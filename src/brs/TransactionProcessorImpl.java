package brs;

import static brs.http.common.ResultFields.UNCONFIRMED_TRANSACTIONS_RESPONSE;

import brs.CaretException.ValidationException;
import brs.props.Props;
import brs.db.store.Dbs;
import brs.db.store.Stores;
import brs.fluxcapacitor.FeatureToggle;
import brs.peer.Peer;
import brs.peer.Peers;
import brs.services.AccountService;
import brs.props.PropertyService;
import brs.services.TimeService;
import brs.services.TransactionService;
import brs.unconfirmedtransactions.UnconfirmedTransactionStore;
import brs.util.Listener;
import brs.util.Listeners;
import brs.util.ThreadPool;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class TransactionProcessorImpl implements TransactionProcessor {

  private static final Logger logger = LoggerFactory.getLogger(TransactionProcessorImpl.class);

  private final boolean testUnconfirmedTransactions;

  private final Object unconfirmedTransactionsSyncObj = new Object();

  private final Listeners<List<? extends Transaction>,Event> transactionListeners = new Listeners<>();

  private final EconomicClustering economicClustering;

  private Stores stores;
  private TimeService timeService;
  private TransactionService transactionService;
  private Dbs dbs;
  private Blockchain blockchain;
  private AccountService accountService;
  private UnconfirmedTransactionStore unconfirmedTransactionStore;
  private Function<Peer, List<Transaction>> foodDispenser = (peer -> unconfirmedTransactionStore.getAllFor(peer));
  private BiConsumer<Peer, List<Transaction>> doneFeedingLog = ((peer, transactions) -> unconfirmedTransactionStore.markFingerPrintsOf(peer, transactions));

  public TransactionProcessorImpl(PropertyService propertyService,
      EconomicClustering economicClustering, Blockchain blockchain, Stores stores, TimeService timeService, Dbs dbs, AccountService accountService,
      TransactionService transactionService, ThreadPool threadPool) {

    this.economicClustering = economicClustering;
    this.blockchain = blockchain;
    this.timeService = timeService;

    this.stores = stores;
    this.dbs = dbs;

    this.accountService = accountService;
    this.transactionService = transactionService;

    this.testUnconfirmedTransactions = propertyService.getBoolean(Props.BRS_TEST_UNCONFIRMED_TRANSACTIONS);

    this.unconfirmedTransactionStore = stores.getUnconfirmedTransactionStore();
    threadPool.scheduleThread("PullUnconfirmedTransactions", getUnconfirmedTransactions, 5);
  }

  private final Runnable getUnconfirmedTransactions = () -> {
    try {
      try {
        synchronized (unconfirmedTransactionsSyncObj) {
          Peer peer = Peers.getAnyPeer(Peer.State.CONNECTED);
          if (peer == null) {
            return;
          }
          JSONObject response = Peers.readUnconfirmedTransactionsNonBlocking(peer).get();
          if (response == null) {
            return;
          }

          JSONArray transactionsData = (JSONArray) response.get(UNCONFIRMED_TRANSACTIONS_RESPONSE);

          if (transactionsData == null) {
            return;
          }
          try {
            List<Transaction> addedTransactions = processPeerTransactions(transactionsData, peer);
            Peers.feedingTime(peer, foodDispenser, doneFeedingLog);

            if(! addedTransactions.isEmpty()) {
              List<Peer> activePrioPlusExtra = Peers.getAllActivePriorityPlusSomeExtraPeers();
              activePrioPlusExtra.remove(peer);

              List<CompletableFuture<?>> expectedResults = new ArrayList<>();

              for(Peer otherPeer:activePrioPlusExtra) {
                CompletableFuture<JSONObject> unconfirmedTransactionsResult = Peers.readUnconfirmedTransactionsNonBlocking(otherPeer);

                unconfirmedTransactionsResult.whenComplete((jsonObject, throwable) -> {
                  try {
                    processPeerTransactions(transactionsData, otherPeer);
                    Peers.feedingTime(otherPeer, foodDispenser, doneFeedingLog);
                  } catch (ValidationException | RuntimeException e) {
                    peer.blacklist(e, "pulled invalid data using getUnconfirmedTransactions");
                  }
                });

                expectedResults.add(unconfirmedTransactionsResult);
              }

              CompletableFuture.allOf(expectedResults.toArray(new CompletableFuture[expectedResults.size()])).join();
            }
          } catch (ValidationException | RuntimeException e) {
            peer.blacklist(e, "pulled invalid data using getUnconfirmedTransactions");
          }
        }
      } catch (Exception e) {
        logger.debug("Error processing unconfirmed transactions", e);
      }

    } catch (Throwable t) {
      logger.info("CRITICAL ERROR. PLEASE REPORT TO THE DEVELOPERS.\n" + t.toString(), t);
      System.exit(1);
    }
  };

  @Override
  public boolean addListener(Listener<List<? extends Transaction>> listener, Event eventType) {
    return transactionListeners.addListener(listener, eventType);
  }

  @Override
  public boolean removeListener(Listener<List<? extends Transaction>> listener, Event eventType) {
    return transactionListeners.removeListener(listener, eventType);
  }

  void notifyListeners(List<? extends Transaction> transactions, Event eventType) {
    transactionListeners.notify(transactions, eventType);
  }

  public Object getUnconfirmedTransactionsSyncObj() {
    return unconfirmedTransactionsSyncObj;
  }

  @Override
  public List<Transaction> getAllUnconfirmedTransactions() {
    return unconfirmedTransactionStore.getAll();
  }

  @Override
  public List<Transaction> getAllUnconfirmedTransactionsFor(Peer peer, long limitInBytes) {
    return unconfirmedTransactionStore.getAllFor(peer);
  }

  @Override
  public void markFingerPrintsOf(Peer peer, List<Transaction> transactions) {
    unconfirmedTransactionStore.markFingerPrintsOf(peer, transactions);
  }

  @Override
  public Transaction getUnconfirmedTransaction(long transactionId) {
    return unconfirmedTransactionStore.get(transactionId);
  }

  @Override
  public Transaction.Builder newTransactionBuilder(byte[] senderPublicKey, long amountNQT, long feeNQT, short deadline, Attachment attachment) {
    byte version = (byte) getTransactionVersion(blockchain.getHeight());
    int timestamp = timeService.getEpochTime();
    Transaction.Builder builder = new Transaction.Builder(version, senderPublicKey, amountNQT, feeNQT, timestamp,
                                                                          deadline, (Attachment.AbstractAttachment)attachment);
    if (version > 0) {
      Block ecBlock = this.economicClustering.getECBlock(timestamp);
      builder.ecBlockHeight(ecBlock.getHeight());
      builder.ecBlockId(ecBlock.getId());
    }
    return builder;
  }

  @Override
  public Integer broadcast(Transaction transaction) throws CaretException.ValidationException {
    if (! transaction.verifySignature()) {
      throw new CaretException.NotValidException("Transaction signature verification failed");
    }
    List<Transaction> processedTransactions;
    if (dbs.getTransactionDb().hasTransaction(transaction.getId())) {
      logger.info("Transaction " + transaction.getStringId() + " already in blockchain, will not broadcast again");
      return null;
    }

    if (unconfirmedTransactionStore.exists(transaction.getId())) {
      logger.info("Transaction " + transaction.getStringId() + " already in unconfirmed pool, will not broadcast again");
      return null;
    }

    processedTransactions = processTransactions(Collections.singleton(transaction), null);

    if(! processedTransactions.isEmpty()) {
      return broadcastToPeers(true);
    } else {
      logger.debug("Could not accept new transaction " + transaction.getStringId());
      throw new CaretException.NotValidException("Invalid transaction " + transaction.getStringId());
    }
  }

  @Override
  public void processPeerTransactions(JSONObject request, Peer peer) throws CaretException.ValidationException {
    JSONArray transactionsData = (JSONArray)request.get("transactions");
    List<Transaction> processedTransactions = processPeerTransactions(transactionsData, peer);

    if(! processedTransactions.isEmpty()) {
      broadcastToPeers(false);
    }
  }

  @Override
  public Transaction parseTransaction(byte[] bytes) throws CaretException.ValidationException {
    return Transaction.parseTransaction(bytes);
  }

  @Override
  public Transaction parseTransaction(JSONObject transactionData) throws CaretException.NotValidException {
    return Transaction.parseTransaction(transactionData, blockchain.getHeight());
  }
    
  @Override
  public void clearUnconfirmedTransactions() {
    synchronized (unconfirmedTransactionsSyncObj) {
      List<Transaction> removed;
      try {
        stores.beginTransaction();
        removed = unconfirmedTransactionStore.getAll();
        accountService.flushAccountTable();
        unconfirmedTransactionStore.clear();
        stores.commitTransaction();
      } catch (Exception e) {
        logger.error(e.toString(), e);
        stores.rollbackTransaction();
        throw e;
      } finally {
        stores.endTransaction();
      }

      transactionListeners.notify(removed, Event.REMOVED_UNCONFIRMED_TRANSACTIONS);
    }
  }

  void requeueAllUnconfirmedTransactions() {
    synchronized (unconfirmedTransactionsSyncObj) {
      unconfirmedTransactionStore.resetAccountBalances();
    }
  }

  int getTransactionVersion(int previousBlockHeight) {
    return Caret.getFluxCapacitor().isActive(FeatureToggle.DIGITAL_GOODS_STORE, previousBlockHeight) ? 1 : 0;
  }

  // Watch: This is not really clean
  void processLater(Collection<Transaction> transactions) {
    for ( Transaction transaction : transactions ) {
      try {
        unconfirmedTransactionStore.put(transaction, null);
      }
      catch ( CaretException.ValidationException e ) {
        logger.debug("Discarding invalid transaction in for later processing: " + transaction.getJSONObject().toJSONString(), e);
      }
    }
  }

  private List<Transaction> processPeerTransactions(JSONArray transactionsData, Peer peer) throws CaretException.ValidationException {
	  if (blockchain.getLastBlock().getTimestamp() < timeService.getEpochTime() - 60 * 1440 && ! testUnconfirmedTransactions) {
      return new ArrayList<>();
    }
    if (blockchain.getHeight() <= Constants.NQT_BLOCK) {
      return new ArrayList<>();
    }
    List<Transaction> transactions = new ArrayList<>();
    for (Object transactionData : transactionsData) {
      try {
        Transaction transaction = parseTransaction((JSONObject) transactionData);
        transactionService.validate(transaction);
        if(!this.economicClustering.verifyFork(transaction)) {
          /*if(Caret.getBlockchain().getHeight() >= Constants.EC_CHANGE_BLOCK_1) {
            throw new CaretException.NotValidException("Transaction from wrong fork");
            }*/
          continue;
        }
        transactions.add(transaction);
      } catch (CaretException.NotCurrentlyValidException ignore) {
      } catch (CaretException.NotValidException e) {
        logger.debug("Invalid transaction from peer: " + ((JSONObject) transactionData).toJSONString());
        throw e;
      }
    }
    return processTransactions(transactions, peer);
  }

  private List<Transaction> processTransactions(Collection<Transaction> transactions, Peer peer) throws CaretException.ValidationException {
    synchronized (unconfirmedTransactionsSyncObj) {
      if (transactions.isEmpty()) {
        return Collections.emptyList();
      }

      List<Transaction> addedUnconfirmedTransactions = new ArrayList<>();

      for (Transaction transaction : transactions) {

        try {
          int curTime = timeService.getEpochTime();
          if (transaction.getTimestamp() > curTime + 15 || transaction.getExpiration() < curTime
              || transaction.getDeadline() > 1440) {
            continue;
          }

          try {
            stores.beginTransaction();
            if (blockchain.getHeight() < Constants.NQT_BLOCK) {
              break; // not ready to process transactions
            }

            if (dbs.getTransactionDb().hasTransaction(transaction.getId()) || unconfirmedTransactionStore.exists(transaction.getId())) {
              stores.commitTransaction();
              unconfirmedTransactionStore.markFingerPrintsOf(peer, Arrays.asList(transaction));
              continue;
            }

            if (!(transaction.verifySignature() && transactionService.verifyPublicKey(transaction))) {
              if (accountService.getAccount(transaction.getSenderId()) != null) {
                logger.debug("Transaction " + transaction.getJSONObject().toJSONString() + " failed to verify");
              }
              stores.commitTransaction();
              continue;
            }

            if(unconfirmedTransactionStore.put(transaction, peer)) {
              addedUnconfirmedTransactions.add(transaction);
            }

            stores.commitTransaction();
          } catch (Exception e) {
            stores.rollbackTransaction();
            throw e;
          } finally {
            stores.endTransaction();
          }
        } catch (RuntimeException e) {
          logger.info("Error processing transaction", e);
        }
      }

      if (! addedUnconfirmedTransactions.isEmpty()) {
        transactionListeners.notify(addedUnconfirmedTransactions, Event.ADDED_UNCONFIRMED_TRANSACTIONS);
      }

      return addedUnconfirmedTransactions;
    }
  }

  private int broadcastToPeers(boolean toAll) {
    List<? extends Peer> peersToSendTo = toAll ? Peers.getActivePeers().stream().limit(100).collect(Collectors.toList()) : Peers.getAllActivePriorityPlusSomeExtraPeers();

    logger.info("Queueing up {} Peers for feeding", peersToSendTo.size());

    for(Peer p: peersToSendTo) {
      Peers.feedingTime(p, foodDispenser, doneFeedingLog);
    }

    return peersToSendTo.size();
  }

  public void revalidateUnconfirmedTransactions() {
    final List<Transaction> invalidTransactions = new ArrayList<>();

    for(Transaction t: unconfirmedTransactionStore.getAll()) {
      try {
        this.transactionService.validate(t);
      } catch (ValidationException e) {
        invalidTransactions.add(t);
      }
    }

    for(Transaction t:invalidTransactions) {
      unconfirmedTransactionStore.remove(t);
    }
  }

  public void removeForgedTransactions(List<Transaction> transactions) {
    this.unconfirmedTransactionStore.removeForgedTransactions(transactions);
  }
}
