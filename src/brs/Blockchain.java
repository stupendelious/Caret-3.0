package brs;

import brs.db.CaretIterator;

import java.util.List;

public interface Blockchain {

  Block getLastBlock();
    
  Block getLastBlock(int timestamp);

  void setLastBlock(Block blockImpl);

  int getHeight();

  Block getBlock(long blockImplId);

  Block getBlockAtHeight(int height);

  boolean hasBlock(long blockImplId);

  CaretIterator<Block> getBlocks(int from, int to);

  CaretIterator<Block> getBlocks(Account account, int timestamp);
    
  CaretIterator<Block> getBlocks(Account account, int timestamp, int from, int to);

  List<Long> getBlockIdsAfter(long blockImplId, int limit);

  List<? extends Block> getBlocksAfter(long blockImplId, int limit);

  long getBlockIdAtHeight(int height);

  Transaction getTransaction(long transactionId);

  Transaction getTransactionByFullHash(String fullHash);

  boolean hasTransaction(long transactionId);

  boolean hasTransactionByFullHash(String fullHash);

  int getTransactionCount();

  CaretIterator<Transaction> getAllTransactions();

  CaretIterator<Transaction> getTransactions(Account account, byte type, byte subtype, int blockImplTimestamp);

  CaretIterator<Transaction> getTransactions(Account account, int numberOfConfirmations, byte type, byte subtype, int blockImplTimestamp, int from, int to);

}
