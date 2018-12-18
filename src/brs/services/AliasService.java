package brs.services;

import brs.Alias;
import brs.Alias.Offer;
import brs.Attachment;
import brs.Transaction;
import brs.db.CaretIterator;

public interface AliasService {

  Alias getAlias(long aliasId);

  Alias getAlias(String aliasName);

  Offer getOffer(Alias alias);

  long getAliasCount();

  CaretIterator<Alias> getAliasesByOwner(long accountId, int from, int to);

  void addOrUpdateAlias(Transaction transaction, Attachment.MessagingAliasAssignment attachment);

  void sellAlias(Transaction transaction, Attachment.MessagingAliasSell attachment);

  void changeOwner(long newOwnerId, String aliasName, int timestamp);
}
