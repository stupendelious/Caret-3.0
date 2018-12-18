package brs;

import brs.peer.Peer;
import brs.util.Observable;
import org.json.simple.JSONObject;

import java.util.List;

public interface TransactionProcessor extends Observable<List<? extends Transaction>,TransactionProcessor.Event> {

  enum Event {
    REMOVED_UNCONFIRMED_TRANSACTIONS,
    ADDED_UNCONFIRMED_TRANSACTIONS,
    ADDED_CONFIRMED_TRANSACTIONS,
    ADDED_DOUBLESPENDING_TRANSACTIONS
  }

  List<Transaction> getAllUnconfirmedTransactions();

  List<Transaction> getAllUnconfirmedTransactionsFor(Peer peer, long limitInBytes);

  void markFingerPrintsOf(Peer peer, List<Transaction> transactions);
  
  Transaction getUnconfirmedTransaction(long transactionId);

  void clearUnconfirmedTransactions();

  Integer broadcast(Transaction transaction) throws CaretException.ValidationException;

  void processPeerTransactions(JSONObject request, Peer peer) throws CaretException.ValidationException;

  Transaction parseTransaction(byte[] bytes) throws CaretException.ValidationException;

  Transaction parseTransaction(JSONObject json) throws CaretException.ValidationException;

  Transaction.Builder newTransactionBuilder(byte[] senderPublicKey, long amountNQT, long feeNQT, short deadline, Attachment attachment);

}
