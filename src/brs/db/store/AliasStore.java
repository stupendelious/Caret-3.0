package brs.db.store;

import brs.Alias;
import brs.db.CaretIterator;
import brs.db.VersionedEntityTable;
import brs.db.CaretKey;

public interface AliasStore {
  CaretKey.LongKeyFactory<Alias> getAliasDbKeyFactory();
  CaretKey.LongKeyFactory<Alias.Offer> getOfferDbKeyFactory();

  VersionedEntityTable<Alias> getAliasTable();

  VersionedEntityTable<Alias.Offer> getOfferTable();

  CaretIterator<Alias> getAliasesByOwner(long accountId, int from, int to);

  Alias getAlias(String aliasName);
}
