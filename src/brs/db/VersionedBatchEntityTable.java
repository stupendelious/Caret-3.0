package brs.db;

import java.util.ArrayList;
import java.util.List;

import org.ehcache.Cache;
import org.jooq.DSLContext;
import org.jooq.Condition;
import org.jooq.SelectQuery;
import org.jooq.SortField;

public interface VersionedBatchEntityTable<T> extends DerivedTable, EntityTable<T> {
  boolean delete(T t);

  @Override
  T get(CaretKey dbKey);

  @Override
  void insert(T t);

  @Override
  void finish();

  @Override
  T get(CaretKey dbKey, int height);

  @Override
  T getBy(Condition condition);

  @Override
  T getBy(Condition condition, int height);

  @Override
  CaretIterator<T> getManyBy(Condition condition, int from, int to, List<SortField> sort);

  @Override
  CaretIterator<T> getManyBy(Condition condition, int height, int from, int to);

  @Override
  CaretIterator<T> getManyBy(Condition condition, int height, int from, int to, List<SortField> sort);

  @Override
  CaretIterator<T> getManyBy(DSLContext ctx, SelectQuery query, boolean cache);

  @Override
  CaretIterator<T> getAll(int from, int to);

  @Override
  CaretIterator<T> getAll(int from, int to, List<SortField> sort);

  @Override
  CaretIterator<T> getAll(int height, int from, int to);

  @Override
  CaretIterator<T> getAll(int height, int from, int to, List<SortField> sort);

  @Override
  int getCount();

  @Override
  int getRowCount();

  @Override
  void rollback(int height);

  @Override
  void truncate();

  Cache getCache();

  void flushCache();

  void fillCache(ArrayList<Long> ids);
}
