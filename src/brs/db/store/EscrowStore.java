package brs.db.store;

import brs.Escrow;
import brs.Transaction;
import brs.db.CaretIterator;
import brs.db.CaretKey;
import brs.db.VersionedEntityTable;
import brs.db.sql.DbKey;
import java.util.Collection;
import java.util.List;

public interface EscrowStore {

  CaretKey.LongKeyFactory<Escrow> getEscrowDbKeyFactory();

  VersionedEntityTable<Escrow> getEscrowTable();

  DbKey.LinkKeyFactory<Escrow.Decision> getDecisionDbKeyFactory();

  VersionedEntityTable<Escrow.Decision> getDecisionTable();

  Collection<Escrow> getEscrowTransactionsByParticipant(Long accountId);

  List<Transaction> getResultTransactions();

  CaretIterator<Escrow.Decision> getDecisions(Long id);
}
