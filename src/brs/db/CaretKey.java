package brs.db;

import java.sql.ResultSet;

public interface CaretKey {

  interface Factory<T> {
    CaretKey newKey(T t);

    CaretKey newKey(ResultSet rs);
  }

  long[] getPKValues();

  interface LongKeyFactory<T> extends Factory<T> {
    @Override
    CaretKey newKey(ResultSet rs);

    CaretKey newKey(long id);

  }

  interface LinkKeyFactory<T> extends Factory<T> {
    CaretKey newKey(long idA, long idB);
  }
}
