package com.vickikbt.cache.daos;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.vickikbt.cache.converters.MovieEntityConverter;
import com.vickikbt.cache.models.MovieEntity;
import com.vickikbt.cache.models.PopularResultEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class PopularShowsDao_Impl implements PopularShowsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PopularResultEntity> __insertionAdapterOfPopularResultEntity;

  private final MovieEntityConverter __movieEntityConverter = new MovieEntityConverter();

  private final SharedSQLiteStatement __preparedStmtOfDeletePopularShows;

  public PopularShowsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPopularResultEntity = new EntityInsertionAdapter<PopularResultEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Popular_Result_Table` (`Page`,`Movies`,`Total_Pages`,`Total_Results`) VALUES (?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PopularResultEntity value) {
        if (value.getPage() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getPage());
        }
        final String _tmp;
        _tmp = __movieEntityConverter.from(value.getMovieEntity());
        if (_tmp == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, _tmp);
        }
        stmt.bindLong(3, value.getTotal_pages());
        if (value.getTotal_results() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, value.getTotal_results());
        }
      }
    };
    this.__preparedStmtOfDeletePopularShows = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Popular_Result_Table";
        return _query;
      }
    };
  }

  @Override
  public Object savePopularShows(final PopularResultEntity popularResultEntity,
      final Continuation<? super Unit> p1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPopularResultEntity.insert(popularResultEntity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, p1);
  }

  @Override
  public Object deletePopularShows(final Continuation<? super Unit> p0) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeletePopularShows.acquire();
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeletePopularShows.release(_stmt);
        }
      }
    }, p0);
  }

  @Override
  public Flow<PopularResultEntity> getPopularShows() {
    final String _sql = "SELECT * FROM Popular_Result_Table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"Popular_Result_Table"}, new Callable<PopularResultEntity>() {
      @Override
      public PopularResultEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPage = CursorUtil.getColumnIndexOrThrow(_cursor, "Page");
          final int _cursorIndexOfMovieEntity = CursorUtil.getColumnIndexOrThrow(_cursor, "Movies");
          final int _cursorIndexOfTotalPages = CursorUtil.getColumnIndexOrThrow(_cursor, "Total_Pages");
          final int _cursorIndexOfTotalResults = CursorUtil.getColumnIndexOrThrow(_cursor, "Total_Results");
          final PopularResultEntity _result;
          if(_cursor.moveToFirst()) {
            final Integer _tmpPage;
            if (_cursor.isNull(_cursorIndexOfPage)) {
              _tmpPage = null;
            } else {
              _tmpPage = _cursor.getInt(_cursorIndexOfPage);
            }
            final List<MovieEntity> _tmpMovieEntity;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfMovieEntity)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfMovieEntity);
            }
            _tmpMovieEntity = __movieEntityConverter.to(_tmp);
            final int _tmpTotal_pages;
            _tmpTotal_pages = _cursor.getInt(_cursorIndexOfTotalPages);
            final Integer _tmpTotal_results;
            if (_cursor.isNull(_cursorIndexOfTotalResults)) {
              _tmpTotal_results = null;
            } else {
              _tmpTotal_results = _cursor.getInt(_cursorIndexOfTotalResults);
            }
            _result = new PopularResultEntity(_tmpPage,_tmpMovieEntity,_tmpTotal_pages,_tmpTotal_results);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object isPopularCacheAvailable(final Continuation<? super Integer> p0) {
    final String _sql = "SELECT COUNT(*) FROM Popular_Result_Table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if(_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, p0);
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
