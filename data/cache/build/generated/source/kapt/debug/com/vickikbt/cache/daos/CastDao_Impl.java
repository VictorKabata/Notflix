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
import com.vickikbt.cache.converters.ActorEntityConverter;
import com.vickikbt.cache.models.ActorEntity;
import com.vickikbt.cache.models.CastEntity;
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
public final class CastDao_Impl implements CastDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CastEntity> __insertionAdapterOfCastEntity;

  private final ActorEntityConverter __actorEntityConverter = new ActorEntityConverter();

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllMovieCast;

  public CastDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCastEntity = new EntityInsertionAdapter<CastEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Casts Table` (`actor`,`id`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CastEntity value) {
        final String _tmp;
        _tmp = __actorEntityConverter.from(value.getActor());
        if (_tmp == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, _tmp);
        }
        if (value.getId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, value.getId());
        }
      }
    };
    this.__preparedStmtOfDeleteAllMovieCast = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM `Casts Table`";
        return _query;
      }
    };
  }

  @Override
  public Object saveMovieCast(final CastEntity castEntity,
      final Continuation<? super Unit> continuation) {
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
    }, continuation);
  }

  @Override
  public Object deleteAllMovieCast(final Continuation<? super Unit> continuation) {
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
    }, continuation);
  }

  @Override
  public Flow<CastEntity> getMovieCast(final int movieId) {
    final String _sql = "SELECT * FROM `Casts Table` WHERE id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, movieId);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"Casts Table"}, new Callable<CastEntity>() {
      @Override
      public CastEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfActor = CursorUtil.getColumnIndexOrThrow(_cursor, "actor");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final CastEntity _result;
          if(_cursor.moveToFirst()) {
            final List<ActorEntity> _tmpActor;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfActor)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfActor);
            }
            _tmpActor = __actorEntityConverter.to(_tmp);
            final Integer _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getInt(_cursorIndexOfId);
            }
            _result = new CastEntity(_tmpActor,_tmpId);
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
  public Object isMovieCastAvailable(final int movieId,
      final Continuation<? super Integer> continuation) {
    final String _sql = "SELECT COUNT(*) FROM `Casts Table` WHERE id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, movieId);
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
    }, continuation);
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
