package brs.db.store;

import brs.Trade;
import brs.db.CaretIterator;
import brs.db.CaretKey;
import brs.db.sql.EntitySqlTable;

public interface TradeStore {
  CaretIterator<Trade> getAllTrades(int from, int to);

  CaretIterator<Trade> getAssetTrades(long assetId, int from, int to);

  CaretIterator<Trade> getAccountTrades(long accountId, int from, int to);

  CaretIterator<Trade> getAccountAssetTrades(long accountId, long assetId, int from, int to);

  int getTradeCount(long assetId);

  CaretKey.LinkKeyFactory<Trade> getTradeDbKeyFactory();

  EntitySqlTable<Trade> getTradeTable();
}
