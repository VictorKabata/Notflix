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
import com.vickikbt.cache.converters.GenreEntityConverter;
import com.vickikbt.cache.converters.SpokenLanguageEntityConverter;
import com.vickikbt.cache.models.GenreEntity;
import com.vickikbt.cache.models.MovieDetailsEntity;
import com.vickikbt.cache.models.SpokenLanguageEntity;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Double;
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
public final class MovieDetailsDao_Impl implements MovieDetailsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MovieDetailsEntity> __insertionAdapterOfMovieDetailsEntity;

  private final GenreEntityConverter __genreEntityConverter = new GenreEntityConverter();

  private final SpokenLanguageEntityConverter __spokenLanguageEntityConverter = new SpokenLanguageEntityConverter();

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllMovieDetails;

  public MovieDetailsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMovieDetailsEntity = new EntityInsertionAdapter<MovieDetailsEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Movie Details Table` (`adult`,`backdropPath`,`genres`,`homepage`,`id`,`imdbId`,`originalLanguage`,`originalTitle`,`overview`,`popularity`,`posterPath`,`releaseDate`,`runtime`,`spokenLanguages`,`status`,`tagline`,`title`,`video`,`voteAverage`,`voteCount`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MovieDetailsEntity value) {
        final Integer _tmp;
        _tmp = value.getAdult() == null ? null : (value.getAdult() ? 1 : 0);
        if (_tmp == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, _tmp);
        }
        if (value.getBackdropPath() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getBackdropPath());
        }
        final String _tmp_1;
        _tmp_1 = __genreEntityConverter.from(value.getGenres());
        if (_tmp_1 == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, _tmp_1);
        }
        if (value.getHomepage() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getHomepage());
        }
        if (value.getId() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindLong(5, value.getId());
        }
        if (value.getImdbId() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getImdbId());
        }
        if (value.getOriginalLanguage() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getOriginalLanguage());
        }
        if (value.getOriginalTitle() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getOriginalTitle());
        }
        if (value.getOverview() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getOverview());
        }
        if (value.getPopularity() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindDouble(10, value.getPopularity());
        }
        if (value.getPosterPath() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getPosterPath());
        }
        if (value.getReleaseDate() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getReleaseDate());
        }
        if (value.getRuntime() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindLong(13, value.getRuntime());
        }
        final String _tmp_2;
        _tmp_2 = __spokenLanguageEntityConverter.from(value.getSpokenLanguages());
        if (_tmp_2 == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, _tmp_2);
        }
        if (value.getStatus() == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindString(15, value.getStatus());
        }
        if (value.getTagline() == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindString(16, value.getTagline());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(17);
        } else {
          stmt.bindString(17, value.getTitle());
        }
        final Integer _tmp_3;
        _tmp_3 = value.getVideo() == null ? null : (value.getVideo() ? 1 : 0);
        if (_tmp_3 == null) {
          stmt.bindNull(18);
        } else {
          stmt.bindLong(18, _tmp_3);
        }
        if (value.getVoteAverage() == null) {
          stmt.bindNull(19);
        } else {
          stmt.bindDouble(19, value.getVoteAverage());
        }
        if (value.getVoteCount() == null) {
          stmt.bindNull(20);
        } else {
          stmt.bindLong(20, value.getVoteCount());
        }
      }
    };
    this.__preparedStmtOfDeleteAllMovieDetails = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM `Movie Details Table`";
        return _query;
      }
    };
  }

  @Override
  public Object saveMovieDetails(final MovieDetailsEntity movieDetails,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfMovieDetailsEntity.insert(movieDetails);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteAllMovieDetails(final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllMovieDetails.acquire();
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteAllMovieDetails.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Flow<MovieDetailsEntity> getMovieDetails(final int movieId) {
    final String _sql = "SELECT * FROM `Movie Details Table` WHERE id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, movieId);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"Movie Details Table"}, new Callable<MovieDetailsEntity>() {
      @Override
      public MovieDetailsEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfAdult = CursorUtil.getColumnIndexOrThrow(_cursor, "adult");
          final int _cursorIndexOfBackdropPath = CursorUtil.getColumnIndexOrThrow(_cursor, "backdropPath");
          final int _cursorIndexOfGenres = CursorUtil.getColumnIndexOrThrow(_cursor, "genres");
          final int _cursorIndexOfHomepage = CursorUtil.getColumnIndexOrThrow(_cursor, "homepage");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfImdbId = CursorUtil.getColumnIndexOrThrow(_cursor, "imdbId");
          final int _cursorIndexOfOriginalLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "originalLanguage");
          final int _cursorIndexOfOriginalTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "originalTitle");
          final int _cursorIndexOfOverview = CursorUtil.getColumnIndexOrThrow(_cursor, "overview");
          final int _cursorIndexOfPopularity = CursorUtil.getColumnIndexOrThrow(_cursor, "popularity");
          final int _cursorIndexOfPosterPath = CursorUtil.getColumnIndexOrThrow(_cursor, "posterPath");
          final int _cursorIndexOfReleaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "releaseDate");
          final int _cursorIndexOfRuntime = CursorUtil.getColumnIndexOrThrow(_cursor, "runtime");
          final int _cursorIndexOfSpokenLanguages = CursorUtil.getColumnIndexOrThrow(_cursor, "spokenLanguages");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfTagline = CursorUtil.getColumnIndexOrThrow(_cursor, "tagline");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfVideo = CursorUtil.getColumnIndexOrThrow(_cursor, "video");
          final int _cursorIndexOfVoteAverage = CursorUtil.getColumnIndexOrThrow(_cursor, "voteAverage");
          final int _cursorIndexOfVoteCount = CursorUtil.getColumnIndexOrThrow(_cursor, "voteCount");
          final MovieDetailsEntity _result;
          if(_cursor.moveToFirst()) {
            final Boolean _tmpAdult;
            final Integer _tmp;
            if (_cursor.isNull(_cursorIndexOfAdult)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(_cursorIndexOfAdult);
            }
            _tmpAdult = _tmp == null ? null : _tmp != 0;
            final String _tmpBackdropPath;
            if (_cursor.isNull(_cursorIndexOfBackdropPath)) {
              _tmpBackdropPath = null;
            } else {
              _tmpBackdropPath = _cursor.getString(_cursorIndexOfBackdropPath);
            }
            final List<GenreEntity> _tmpGenres;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfGenres)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfGenres);
            }
            _tmpGenres = __genreEntityConverter.to(_tmp_1);
            final String _tmpHomepage;
            if (_cursor.isNull(_cursorIndexOfHomepage)) {
              _tmpHomepage = null;
            } else {
              _tmpHomepage = _cursor.getString(_cursorIndexOfHomepage);
            }
            final Integer _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getInt(_cursorIndexOfId);
            }
            final String _tmpImdbId;
            if (_cursor.isNull(_cursorIndexOfImdbId)) {
              _tmpImdbId = null;
            } else {
              _tmpImdbId = _cursor.getString(_cursorIndexOfImdbId);
            }
            final String _tmpOriginalLanguage;
            if (_cursor.isNull(_cursorIndexOfOriginalLanguage)) {
              _tmpOriginalLanguage = null;
            } else {
              _tmpOriginalLanguage = _cursor.getString(_cursorIndexOfOriginalLanguage);
            }
            final String _tmpOriginalTitle;
            if (_cursor.isNull(_cursorIndexOfOriginalTitle)) {
              _tmpOriginalTitle = null;
            } else {
              _tmpOriginalTitle = _cursor.getString(_cursorIndexOfOriginalTitle);
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
            final String _tmpPosterPath;
            if (_cursor.isNull(_cursorIndexOfPosterPath)) {
              _tmpPosterPath = null;
            } else {
              _tmpPosterPath = _cursor.getString(_cursorIndexOfPosterPath);
            }
            final String _tmpReleaseDate;
            if (_cursor.isNull(_cursorIndexOfReleaseDate)) {
              _tmpReleaseDate = null;
            } else {
              _tmpReleaseDate = _cursor.getString(_cursorIndexOfReleaseDate);
            }
            final Integer _tmpRuntime;
            if (_cursor.isNull(_cursorIndexOfRuntime)) {
              _tmpRuntime = null;
            } else {
              _tmpRuntime = _cursor.getInt(_cursorIndexOfRuntime);
            }
            final List<SpokenLanguageEntity> _tmpSpokenLanguages;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfSpokenLanguages)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfSpokenLanguages);
            }
            _tmpSpokenLanguages = __spokenLanguageEntityConverter.to(_tmp_2);
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            final String _tmpTagline;
            if (_cursor.isNull(_cursorIndexOfTagline)) {
              _tmpTagline = null;
            } else {
              _tmpTagline = _cursor.getString(_cursorIndexOfTagline);
            }
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final Boolean _tmpVideo;
            final Integer _tmp_3;
            if (_cursor.isNull(_cursorIndexOfVideo)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getInt(_cursorIndexOfVideo);
            }
            _tmpVideo = _tmp_3 == null ? null : _tmp_3 != 0;
            final Double _tmpVoteAverage;
            if (_cursor.isNull(_cursorIndexOfVoteAverage)) {
              _tmpVoteAverage = null;
            } else {
              _tmpVoteAverage = _cursor.getDouble(_cursorIndexOfVoteAverage);
            }
            final Integer _tmpVoteCount;
            if (_cursor.isNull(_cursorIndexOfVoteCount)) {
              _tmpVoteCount = null;
            } else {
              _tmpVoteCount = _cursor.getInt(_cursorIndexOfVoteCount);
            }
            _result = new MovieDetailsEntity(_tmpAdult,_tmpBackdropPath,_tmpGenres,_tmpHomepage,_tmpId,_tmpImdbId,_tmpOriginalLanguage,_tmpOriginalTitle,_tmpOverview,_tmpPopularity,_tmpPosterPath,_tmpReleaseDate,_tmpRuntime,_tmpSpokenLanguages,_tmpStatus,_tmpTagline,_tmpTitle,_tmpVideo,_tmpVoteAverage,_tmpVoteCount);
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
  public Object isMovieDetailsAvailable(final int movieId,
      final Continuation<? super Integer> continuation) {
    final String _sql = "SELECT COUNT(*) FROM `Movie Details Table` WHERE id=?";
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
