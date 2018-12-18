package brs.db.store;

import brs.Subscription;
import brs.db.CaretIterator;
import brs.db.CaretKey;
import brs.db.VersionedEntityTable;

public interface SubscriptionStore {

  CaretKey.LongKeyFactory<Subscription> getSubscriptionDbKeyFactory();

  VersionedEntityTable<Subscription> getSubscriptionTable();

  CaretIterator<Subscription> getSubscriptionsByParticipant(Long accountId);

  CaretIterator<Subscription> getIdSubscriptions(Long accountId);

  CaretIterator<Subscription> getSubscriptionsToId(Long accountId);

  CaretIterator<Subscription> getUpdateSubscriptions(int timestamp);
}
