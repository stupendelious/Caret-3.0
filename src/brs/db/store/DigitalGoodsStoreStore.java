package brs.db.store;

import brs.DigitalGoodsStore;
import brs.crypto.EncryptedData;
import brs.db.CaretIterator;
import brs.db.CaretKey;
import brs.db.VersionedEntityTable;
import brs.db.VersionedValuesTable;


public interface DigitalGoodsStoreStore {

  CaretKey.LongKeyFactory<DigitalGoodsStore.Purchase> getFeedbackDbKeyFactory();

  CaretKey.LongKeyFactory<DigitalGoodsStore.Purchase> getPurchaseDbKeyFactory();

  VersionedEntityTable<DigitalGoodsStore.Purchase> getPurchaseTable();

  VersionedValuesTable<DigitalGoodsStore.Purchase, EncryptedData> getFeedbackTable();

  CaretKey.LongKeyFactory<DigitalGoodsStore.Purchase> getPublicFeedbackDbKeyFactory();

  VersionedValuesTable<DigitalGoodsStore.Purchase, String> getPublicFeedbackTable();

  CaretKey.LongKeyFactory<DigitalGoodsStore.Goods> getGoodsDbKeyFactory();

  VersionedEntityTable<DigitalGoodsStore.Goods> getGoodsTable();

  CaretIterator<DigitalGoodsStore.Goods> getGoodsInStock(int from, int to);

  CaretIterator<DigitalGoodsStore.Goods> getSellerGoods(long sellerId, boolean inStockOnly, int from, int to);

  CaretIterator<DigitalGoodsStore.Purchase> getAllPurchases(int from, int to);

  CaretIterator<DigitalGoodsStore.Purchase> getSellerPurchases(long sellerId, int from, int to);

  CaretIterator<DigitalGoodsStore.Purchase> getBuyerPurchases(long buyerId, int from, int to);

  CaretIterator<DigitalGoodsStore.Purchase> getSellerBuyerPurchases(long sellerId, long buyerId, int from, int to);

  CaretIterator<DigitalGoodsStore.Purchase> getPendingSellerPurchases(long sellerId, int from, int to);

  CaretIterator<DigitalGoodsStore.Purchase> getExpiredPendingPurchases(int timestamp);
}
