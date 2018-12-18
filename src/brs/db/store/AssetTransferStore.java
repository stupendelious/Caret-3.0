package brs.db.store;

import brs.AssetTransfer;
import brs.db.CaretIterator;
import brs.db.CaretKey;
import brs.db.sql.EntitySqlTable;

public interface AssetTransferStore {
  CaretKey.LongKeyFactory<AssetTransfer> getTransferDbKeyFactory();

  EntitySqlTable<AssetTransfer> getAssetTransferTable();

  CaretIterator<AssetTransfer> getAssetTransfers(long assetId, int from, int to);

  CaretIterator<AssetTransfer> getAccountAssetTransfers(long accountId, int from, int to);

  CaretIterator<AssetTransfer> getAccountAssetTransfers(long accountId, long assetId, int from, int to);

  int getTransferCount(long assetId);
}
