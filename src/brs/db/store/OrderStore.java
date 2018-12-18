package brs.db.store;

import brs.Order;
import brs.db.CaretIterator;
import brs.db.CaretKey;
import brs.db.VersionedEntityTable;

public interface OrderStore {
  VersionedEntityTable<Order.Bid> getBidOrderTable();

  CaretIterator<Order.Ask> getAskOrdersByAccountAsset(long accountId, long assetId, int from, int to);

  CaretIterator<Order.Ask> getSortedAsks(long assetId, int from, int to);

  Order.Ask getNextOrder(long assetId);

  CaretIterator<Order.Ask> getAll(int from, int to);

  CaretIterator<Order.Ask> getAskOrdersByAccount(long accountId, int from, int to);

  CaretIterator<Order.Ask> getAskOrdersByAsset(long assetId, int from, int to);

  CaretKey.LongKeyFactory<Order.Ask> getAskOrderDbKeyFactory();

  VersionedEntityTable<Order.Ask> getAskOrderTable();

  CaretKey.LongKeyFactory<Order.Bid> getBidOrderDbKeyFactory();

  CaretIterator<Order.Bid> getBidOrdersByAccount(long accountId, int from, int to);

  CaretIterator<Order.Bid> getBidOrdersByAsset(long assetId, int from, int to);

  CaretIterator<Order.Bid> getBidOrdersByAccountAsset(long accountId, long assetId, int from, int to);

  CaretIterator<Order.Bid> getSortedBids(long assetId, int from, int to);

  Order.Bid getNextBid(long assetId);
}
