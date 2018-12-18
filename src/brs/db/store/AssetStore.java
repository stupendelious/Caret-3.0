package brs.db.store;

import brs.Asset;
import brs.db.CaretIterator;
import brs.db.CaretKey;
import brs.db.sql.EntitySqlTable;

public interface AssetStore {
  CaretKey.LongKeyFactory<Asset> getAssetDbKeyFactory();

  EntitySqlTable<Asset> getAssetTable();

  CaretIterator<Asset> getAssetsIssuedBy(long accountId, int from, int to);
}
