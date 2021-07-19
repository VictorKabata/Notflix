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
import com.vickikbt.cache.models.UpcomingDatesEntity;
import com.vickikbt.cache.models.UpcomingResultEntity;
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
public final class UpcomingShowsDao_Impl implements UpcomingShowsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UpcomingResultEntity> __insertionAdapterOfUpcomingResultEntity;

  private final MovieEntityConverter __movieEntityConverter = new MovieEntityConverter();

  private final SharedSQLiteStatement __preparedStmtOfDeleteUpcomingShows;

  public UpcomingShowsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUpcomingResultEntity = new EntityInsertionAdapter<UpcomingResultEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Upcoming_Result_Table` (`Page`,`Movies`,`Total_Page`,`Total_Results`,`Upcoming_Datesmaximum`,`Upcoming_Datesminimum`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UpcomingResultEntity value) {
        if (value.getPage() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getPage());
        }
        final String _tmp;
        _tmp = __movieEntityConverter.from(value.getMoviesEntity());
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
        final UpcomingDatesEntity _tmpUpcomingDates = value.getUpcomingDates();
        if(_tmpUpcomingDates != null) {
          if (_tmpUpcomingDates.getMaximum() == null) {
            stmt.bindNull(5);
          } else {
            stmt.bindString(5, _tmpUpcomingDates.getMaximum());
          }
          if (_tmpUpcomingDates.getMinimum() == null) {
            stmt.bindNull(6);
          } else {
            stmt.bindString(6, _tmpUpcomingDates.getMinimum());
          }
        } else {
          stmt.bindNull(5);
          stmt.bindNull(6);
        }
      }
    };
    this.__preparedStmtOfDeleteUpcomingShows = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Upcoming_Result_Table";
        return _query;
      }
    };
  }

  @Override
  public Object saveUpcomingShows(final UpcomingResultEntity upcomingResultEntity,
      final Continuation<? super Unit> p1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfUpcomingResultEntity.insert(upcomingResultEntity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, p1);
  }

  @Override
  public Object deleteUpcomingShows(final Continuation<? super Unit> p0) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteUpcomingShows.acquire();
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteUpcomingShows.release(_stmt);
        }
      }
    }, p0);
  }

  @Override
  public Flow<UpcomingResultEntity> getUpcomingShows() {
    final String _sql = "SELECT * FROM Upcoming_Result_Table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"Upcoming_Result_Table"}, new Callable<UpcomingResultEntity>() {
      @Override
      public UpcomingResultEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPage = CursorUtil.getColumnIndexOrThrow(_cursor, "Page");
          final int _cursorIndexOfMoviesEntity = CursorUtil.getColumnIndexOrThrow(_cursor, "Movies");
          final int _cursorIndexOfTotalPages = CursorUtil.getColumnIndexOrThrow(_cursor, "Total_Page");
          final int _cursorIndexOfTotalResults = CursorUtil.getColumnIndexOrThrow(_cursor, "Total_Results");
          final int _cursorIndexOfMaximum = CursorUtil.getColumnIndexOrThrow(_cursor, "Upcoming_Datesmaximum");
          final int _cursorIndexOfMinimum = CursorUtil.getColumnIndexOrThrow(_cursor, "Upcoming_Datesminimum");
          final UpcomingResultEntity _result;
          if(_cursor.moveToFirst()) {
            final Integer _tmpPage;
            if (_cursor.isNull(_cursorIndexOfPage)) {
              _tmpPage = null;
            } else {
              _tmpPage = _cursor.getInt(_cursorIndexOfPage);
            }
            final List<MovieEntity> _tmpMoviesEntity;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfMoviesEntity)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfMoviesEntity);
            }
            _tmpMoviesEntity = __movieEntityConverter.to(_tmp);
            final int _tmpTotal_pages;
            _tmpTotal_pages = _cursor.getInt(_cursorIndexOfTotalPages);
            final Integer _tmpTotal_results;
            if (_cursor.isNull(_cursorIndexOfTotalResults)) {
              _tmpTotal_results = null;
            } else {
              _tmpTotal_results = _cursor.getInt(_cursorIndexOfTotalResults);
            }
            final UpcomingDatesEntity _tmpUpcomingDates;
            if (! (_cursor.isNull(_cursorIndexOfMaximum) && _cursor.isNull(_cursorIndexOfMinimum))) {
              final String _tmpMaximum;
              if (_cursor.isNull(_cursorIndexOfMaximum)) {
                _tmpMaximum = null;
              } else {
                _tmpMaximum = _cursor.getString(_cursorIndexOfMaximum);
              }
              final String _tmpMinimum;
              if (_cursor.isNull(_cursorIndexOfMinimum)) {
                _tmpMinimum = null;
              } else {
                _tmpMinimum = _cursor.getString(_cursorIndexOfMinimum);
              }
              _tmpUpcomingDates = new UpcomingDatesEntity(_tmpMaximum,_tmpMinimum);
            }  else  {
              _tmpUpcomingDates = null;
            }
            _result = new UpcomingResultEntity(_tmpUpcomingDates,_tmpPage,_tmpMoviesEntity,_tmpTotal_pages,_tmpTotal_results);
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
  public Object isUpcomingCacheAvailable(final Continuation<? super Integer> p0) {
    final String _sql = "SELECT COUNT(*) FROM Upcoming_Result_Table";
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
