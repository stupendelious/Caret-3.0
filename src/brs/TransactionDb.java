package brs;

import brs.schema.tables.records.TransactionRecord;

import java.sql.ResultSet;

import java.util.List;
import org.jooq.DSLContext;

public interface TransactionDb {
  Transaction findTransaction(long transactionId);

  Transaction findTransactionByFullHash(String fullHash);

  boolean hasTransaction(long transactionId);

  boolean hasTransactionByFullHash(String fullHash);

  Transaction loadTransaction(TransactionRecord transactionRecord) throws CaretException.ValidationException;

  Transaction loadTransaction(DSLContext ctx, ResultSet rs) throws CaretException.ValidationException;

  List<Transaction> findBlockTransactions(long blockId);

  void saveTransactions(List<Transaction> transactions);

}
