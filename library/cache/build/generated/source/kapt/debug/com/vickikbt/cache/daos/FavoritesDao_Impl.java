package com.vickikbt.cache.daos;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.vickikbt.cache.converters.GenreIDEntityConverter;
import com.vickikbt.cache.models.FavoriteMovieEntity;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class FavoritesDao_Impl implements FavoritesDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<FavoriteMovieEntity> __insertionAdapterOfFavoriteMovieEntity;

  private final GenreIDEntityConverter __genreIDEntityConverter = new GenreIDEntityConverter();

  public FavoritesDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFavoriteMovieEntity = new EntityInsertionAdapter<FavoriteMovieEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Favorite Movies Table` (`Adult`,`Backdrop_Path`,`Genre_IDs`,`ID`,`Original_Language`,`Original_Title`,`Overview`,`Popularity`,`Poster_Path`,`Release_Date`,`Title`,`Video`,`Vote_Average`,`Vote_Count`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FavoriteMovieEntity value) {
        final Integer _tmp;
        _tmp = value.getAdult() == null ? null : (value.getAdult() ? 1 : 0);
        if (_tmp == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, _tmp);
        }
        if (value.getBackdrop_path() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getBackdrop_path());
        }
        final String _tmp_1;
        _tmp_1 = __genreIDEntityConverter.from(value.getGenre_ids());
        if (_tmp_1 == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, _tmp_1);
        }
        stmt.bindLong(4, value.getId());
        if (value.getOriginal_language() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getOriginal_language());
        }
        if (value.getOriginal_title() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getOriginal_title());
        }
        if (value.getOverview() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getOverview());
        }
        if (value.getPopularity() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindDouble(8, value.getPopularity());
        }
        if (value.getPoster_path() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getPoster_path());
        }
        if (value.getRelease_date() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getRelease_date());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getTitle());
        }
        final Integer _tmp_2;
        _tmp_2 = value.getVideo() == null ? null : (value.getVideo() ? 1 : 0);
        if (_tmp_2 == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindLong(12, _tmp_2);
        }
        if (value.getVote_average() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindDouble(13, value.getVote_average());
        }
        if (value.getVote_count() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindLong(14, value.getVote_count());
        }
      }
    };
  }

  @Override
  public Object saveFavoriteMovie(final FavoriteMovieEntity favoriteMovieEntity,
      final Continuation<? super Unit> p1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfFavoriteMovieEntity.insert(favoriteMovieEntity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, p1);
  }

  @Override
  public Flow<List<FavoriteMovieEntity>> getFavoriteMovies() {
    final String _sql = "SELECT * FROM `Favorite Movies Table`";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"Favorite Movies Table"}, new Callable<List<FavoriteMovieEntity>>() {
      @Override
      public List<FavoriteMovieEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfAdult = CursorUtil.getColumnIndexOrThrow(_cursor, "Adult");
          final int _cursorIndexOfBackdropPath = CursorUtil.getColumnIndexOrThrow(_cursor, "Backdrop_Path");
          final int _cursorIndexOfGenreIds = CursorUtil.getColumnIndexOrThrow(_cursor, "Genre_IDs");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "ID");
          final int _cursorIndexOfOriginalLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "Original_Language");
          final int _cursorIndexOfOriginalTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "Original_Title");
          final int _cursorIndexOfOverview = CursorUtil.getColumnIndexOrThrow(_cursor, "Overview");
          final int _cursorIndexOfPopularity = CursorUtil.getColumnIndexOrThrow(_cursor, "Popularity");
          final int _cursorIndexOfPosterPath = CursorUtil.getColumnIndexOrThrow(_cursor, "Poster_Path");
          final int _cursorIndexOfReleaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "Release_Date");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "Title");
          final int _cursorIndexOfVideo = CursorUtil.getColumnIndexOrThrow(_cursor, "Video");
          final int _cursorIndexOfVoteAverage = CursorUtil.getColumnIndexOrThrow(_cursor, "Vote_Average");
          final int _cursorIndexOfVoteCount = CursorUtil.getColumnIndexOrThrow(_cursor, "Vote_Count");
          final List<FavoriteMovieEntity> _result = new ArrayList<FavoriteMovieEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final FavoriteMovieEntity _item;
            final Boolean _tmpAdult;
            final Integer _tmp;
            if (_cursor.isNull(_cursorIndexOfAdult)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(_cursorIndexOfAdult);
            }
            _tmpAdult = _tmp == null ? null : _tmp != 0;
            final String _tmpBackdrop_path;
            if (_cursor.isNull(_cursorIndexOfBackdropPath)) {
              _tmpBackdrop_path = null;
            } else {
              _tmpBackdrop_path = _cursor.getString(_cursorIndexOfBackdropPath);
            }
            final List<Integer> _tmpGenre_ids;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfGenreIds)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfGenreIds);
            }
            _tmpGenre_ids = __genreIDEntityConverter.to(_tmp_1);
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpOriginal_language;
            if (_cursor.isNull(_cursorIndexOfOriginalLanguage)) {
              _tmpOriginal_language = null;
            } else {
              _tmpOriginal_language = _cursor.getString(_cursorIndexOfOriginalLanguage);
            }
            final String _tmpOriginal_title;
            if (_cursor.isNull(_cursorIndexOfOriginalTitle)) {
              _tmpOriginal_title = null;
            } else {
              _tmpOriginal_title = _cursor.getString(_cursorIndexOfOriginalTitle);
            }
            final String _tmpOverview;
            if (_cursor.isNull(_cursorIndexOfOverview)) {
              _tmpOverview = null;
            } else {
              _tmpOverview = _cursor.getString(_cursorIndexOfOverview);
            }
            final Double _tmpPopularity;
            if (_cursor.isNull(_cursorIndexOfPopularity)) {
              _tmpPopularity = null;
            } else {
              _tmpPopularity = _cursor.getDouble(_cursorIndexOfPopularity);
            }
            final String _tmpPoster_path;
            if (_cursor.isNull(_cursorIndexOfPosterPath)) {
              _tmpPoster_path = null;
            } else {
              _tmpPoster_path = _cursor.getString(_cursorIndexOfPosterPath);
            }
            final String _tmpRelease_date;
            if (_cursor.isNull(_cursorIndexOfReleaseDate)) {
              _tmpRelease_date = null;
            } else {
              _tmpRelease_date = _cursor.getString(_cursorIndexOfReleaseDate);
            }
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final Boolean _tmpVideo;
            final Integer _tmp_2;
            if (_cursor.isNull(_cursorIndexOfVideo)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getInt(_cursorIndexOfVideo);
            }
            _tmpVideo = _tmp_2 == null ? null : _tmp_2 != 0;
            final Double _tmpVote_average;
            if (_cursor.isNull(_cursorIndexOfVoteAverage)) {
              _tmpVote_average = null;
            } else {
              _tmpVote_average = _cursor.getDouble(_cursorIndexOfVoteAverage);
            }
            final Integer _tmpVote_count;
            if (_cursor.isNull(_cursorIndexOfVoteCount)) {
              _tmpVote_count = null;
            } else {
              _tmpVote_count = _cursor.getInt(_cursorIndexOfVoteCount);
            }
            _item = new FavoriteMovieEntity(_tmpAdult,_tmpBackdrop_path,_tmpGenre_ids,_tmpId,_tmpOriginal_language,_tmpOriginal_title,_tmpOverview,_tmpPopularity,_tmpPoster_path,_tmpRelease_date,_tmpTitle,_tmpVideo,_tmpVote_average,_tmpVote_count);
            _result.add(_item);
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

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
