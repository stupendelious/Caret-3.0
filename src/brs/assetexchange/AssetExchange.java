package brs.assetexchange;

import brs.Account.AccountAsset;
import brs.Asset;
import brs.AssetTransfer;
import brs.Attachment.ColoredCoinsAskOrderPlacement;
import brs.Attachment.ColoredCoinsAssetIssuance;
import brs.Attachment.ColoredCoinsAssetTransfer;
import brs.Attachment.ColoredCoinsBidOrderPlacement;
import brs.Order;
import brs.Order.Ask;
import brs.Order.Bid;
import brs.Trade;
import brs.Trade.Event;
import brs.Transaction;
import brs.db.CaretIterator;
import brs.util.Listener;

public interface AssetExchange {

  CaretIterator<Asset> getAllAssets(int from, int to);

  Asset getAsset(long assetId);

  int getTradeCount(long assetId);

  int getTransferCount(long id);

  int getAssetAccountsCount(long id);

  void addTradeListener(Listener<Trade> listener, Event trade);

  Ask getAskOrder(long orderId);

  void addAsset(Transaction transaction, ColoredCoinsAssetIssuance attachment);

  void addAssetTransfer(Transaction transaction, ColoredCoinsAssetTransfer attachment);

  void addAskOrder(Transaction transaction, ColoredCoinsAskOrderPlacement attachment);

  void addBidOrder(Transaction transaction, ColoredCoinsBidOrderPlacement attachment);

  void removeAskOrder(long orderId);

  Order.Bid getBidOrder(long orderId);

  void removeBidOrder(long orderId);

  CaretIterator<Trade> getAllTrades(int i, int i1);

  CaretIterator<Trade> getTrades(long assetId, int from, int to);

  CaretIterator<Trade> getAccountTrades(long accountId, int from, int to);

  CaretIterator<Trade> getAccountAssetTrades(long accountId, long assetId, int from, int to);

  CaretIterator<AccountAsset> getAccountAssetsOverview(long accountId, int height, int from, int to);

  CaretIterator<Asset> getAssetsIssuedBy(long accountId, int from, int to);

  CaretIterator<AssetTransfer> getAssetTransfers(long assetId, int from, int to);

  CaretIterator<AssetTransfer> getAccountAssetTransfers(long id, long id1, int from, int to);

  int getAssetsCount();

  int getAskCount();

  int getBidCount();

  int getTradesCount();

  int getAssetTransferCount();

  CaretIterator<Ask> getAskOrdersByAccount(long accountId, int from, int to);

  CaretIterator<Ask> getAskOrdersByAccountAsset(long accountId, long assetId, int from, int to);

  CaretIterator<Bid> getBidOrdersByAccount(long accountId, int from, int to);

  CaretIterator<Bid> getBidOrdersByAccountAsset(long accountId, long assetId, int from, int to);

  CaretIterator<Ask> getAllAskOrders(int from, int to);

  CaretIterator<Bid> getAllBidOrders(int from, int to);

  CaretIterator<Ask> getSortedAskOrders(long assetId, int from, int to);

  CaretIterator<Bid> getSortedBidOrders(long assetId, int from, int to);

}
