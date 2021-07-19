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
import com.vickikbt.cache.converters.CastItemEntityConverter;
import com.vickikbt.cache.models.CastEntity;
import com.vickikbt.cache.models.CastItemEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class CastDao_Impl implements CastDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CastEntity> __insertionAdapterOfCastEntity;

  private final CastItemEntityConverter __castItemEntityConverter = new CastItemEntityConverter();

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllMovieCast;

  public CastDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCastEntity = new EntityInsertionAdapter<CastEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Cast_Table` (`Cast`,`ID`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CastEntity value) {
        final String _tmp;
        _tmp = __castItemEntityConverter.from(value.getCast());
        if (_tmp == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, _tmp);
        }
        stmt.bindLong(2, value.getId());
      }
    };
    this.__preparedStmtOfDeleteAllMovieCast = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Cast_Table";
        return _query;
      }
    };
  }

  @Override
  public Object saveMovieCast(final CastEntity castEntity, final Continuation<? super Unit> p1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfCastEntity.insert(castEntity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, p1);
  }

  @Override
  public Object deleteAllMovieCast(final Continuation<? super Unit> p0) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllMovieCast.acquire();
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteAllMovieCast.release(_stmt);
        }
      }
    }, p0);
  }

  @Override
  public Object getMovieCast(final int movieId, final Continuation<? super CastEntity> p1) {
    final String _sql = "SELECT * FROM Cast_Table WHERE ID=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, movieId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<CastEntity>() {
      @Override
      public CastEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCast = CursorUtil.getColumnIndexOrThrow(_cursor, "Cast");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "ID");
          final CastEntity _result;
          if(_cursor.moveToFirst()) {
            final List<CastItemEntity> _tmpCast;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfCast)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfCast);
            }
            _tmpCast = __castItemEntityConverter.to(_tmp);
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _result = new CastEntity(_tmpCast,_tmpId);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, p1);
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
