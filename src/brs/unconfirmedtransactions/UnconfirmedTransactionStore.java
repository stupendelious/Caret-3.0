package brs.unconfirmedtransactions;

import brs.CaretException;
import brs.Transaction;
import brs.peer.Peer;
import java.util.List;
import java.util.function.Consumer;

public interface UnconfirmedTransactionStore {

  boolean put(Transaction transaction, Peer peer) throws CaretException.ValidationException;

  Transaction get(Long transactionId);

  boolean exists(Long transactionId);

  List<Transaction> getAll();

  List<Transaction> getAllFor(Peer peer);

  void remove(Transaction transaction);

  void clear();

  /**
   * Review which transactions are still eligible to stay
   * @return The list of removed transactions
   */
  void resetAccountBalances();

  void markFingerPrintsOf(Peer peer, List<Transaction> transactions);

  void removeForgedTransactions(List<Transaction> transactions);
}
