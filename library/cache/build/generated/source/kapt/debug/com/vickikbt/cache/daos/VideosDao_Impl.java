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
import com.vickikbt.cache.converters.VideoItemEntityConverter;
import com.vickikbt.cache.models.VideoEntity;
import com.vickikbt.cache.models.VideoItemEntity;
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
public final class VideosDao_Impl implements VideosDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<VideoEntity> __insertionAdapterOfVideoEntity;

  private final VideoItemEntityConverter __videoItemEntityConverter = new VideoItemEntityConverter();

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllMovieVideos;

  public VideosDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfVideoEntity = new EntityInsertionAdapter<VideoEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Videos_Table` (`ID`,`Video Items`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, VideoEntity value) {
        stmt.bindLong(1, value.getId());
        final String _tmp;
        _tmp = __videoItemEntityConverter.from(value.getVideoItemsEntity());
        if (_tmp == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, _tmp);
        }
      }
    };
    this.__preparedStmtOfDeleteAllMovieVideos = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Videos_Table";
        return _query;
      }
    };
  }

  @Override
  public Object saveMovieVideo(final VideoEntity videoEntity, final Continuation<? super Unit> p1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfVideoEntity.insert(videoEntity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, p1);
  }

  @Override
  public Object deleteAllMovieVideos(final Continuation<? super Unit> p0) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllMovieVideos.acquire();
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteAllMovieVideos.release(_stmt);
        }
      }
    }, p0);
  }

  @Override
  public Object getMovieVideo(final int movieId, final Continuation<? super VideoEntity> p1) {
    final String _sql = "SELECT * FROM Videos_Table WHERE ID=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, movieId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<VideoEntity>() {
      @Override
      public VideoEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "ID");
          final int _cursorIndexOfVideoItemsEntity = CursorUtil.getColumnIndexOrThrow(_cursor, "Video Items");
          final VideoEntity _result;
          if(_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final List<VideoItemEntity> _tmpVideoItemsEntity;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfVideoItemsEntity)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfVideoItemsEntity);
            }
            _tmpVideoItemsEntity = __videoItemEntityConverter.to(_tmp);
            _result = new VideoEntity(_tmpId,_tmpVideoItemsEntity);
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
