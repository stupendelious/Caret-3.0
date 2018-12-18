package brs.db.store;

import brs.Account;
import brs.Block;
import brs.Transaction;
import brs.db.CaretIterator;

import java.util.List;
import java.sql.ResultSet;

import org.jooq.DSLContext;

/**
 * Store for both BlockchainImpl and BlockchainProcessorImpl
 */

public interface BlockchainStore {


  CaretIterator<Block> getBlocks(int from, int to);

  CaretIterator<Block> getBlocks(Account account, int timestamp, int from, int to);

  CaretIterator<Block> getBlocks(DSLContext ctx, ResultSet rs);

  List<Long> getBlockIdsAfter(long blockId, int limit);

  List<Block> getBlocksAfter(long blockId, int limit);

  int getTransactionCount();

  CaretIterator<Transaction> getAllTransactions();

  CaretIterator<Transaction> getTransactions(Account account, int numberOfConfirmations, byte type, byte subtype,
                                                 int blockTimestamp, int from, int to);

  CaretIterator<Transaction> getTransactions(DSLContext ctx, ResultSet rs);

  boolean addBlock(Block block);

  void scan(int height);

  CaretIterator<Block> getLatestBlocks(int amountBlocks);
}
