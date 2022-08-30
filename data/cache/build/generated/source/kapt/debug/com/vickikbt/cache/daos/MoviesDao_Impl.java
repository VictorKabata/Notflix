package com.vickikbt.cache.daos;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.paging.DataSource;
import androidx.paging.PagingSource;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.paging.LimitOffsetDataSource;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.vickikbt.cache.converters.GenreIDEntityConverter;
import com.vickikbt.cache.models.MovieEntity;
import com.vickikbt.domain.models.Movie;
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
public final class MoviesDao_Impl implements MoviesDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MovieEntity> __insertionAdapterOfMovieEntity;

  private final GenreIDEntityConverter __genreIDEntityConverter = new GenreIDEntityConverter();

  private final SharedSQLiteStatement __preparedStmtOfDeleteMovies;

  private final SharedSQLiteStatement __preparedStmtOfUpdateMovieIsFavorite;

  public MoviesDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMovieEntity = new EntityInsertionAdapter<MovieEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `Movies Table` (`adult`,`backdropPath`,`genreIds`,`id`,`originalLanguage`,`originalTitle`,`overview`,`popularity`,`posterPath`,`releaseDate`,`title`,`video`,`voteAverage`,`voteCount`,`category`,`isFavorite`,`cacheId`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,nullif(?, 0))";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MovieEntity value) {
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
        _tmp_1 = __genreIDEntityConverter.from(value.getGenreIds());
        if (_tmp_1 == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, _tmp_1);
        }
        if (value.getId() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, value.getId());
        }
        if (value.getOriginalLanguage() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getOriginalLanguage());
        }
        if (value.getOriginalTitle() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getOriginalTitle());
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
        if (value.getPosterPath() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getPosterPath());
        }
        if (value.getReleaseDate() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getReleaseDate());
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
        if (value.getVoteAverage() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindDouble(13, value.getVoteAverage());
        }
        if (value.getVoteCount() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindLong(14, value.getVoteCount());
        }
        if (value.getCategory() == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindString(15, value.getCategory());
        }
        final int _tmp_3;
        _tmp_3 = value.isFavorite() ? 1 : 0;
        stmt.bindLong(16, _tmp_3);
        stmt.bindLong(17, value.getCacheId());
      }
    };
    this.__preparedStmtOfDeleteMovies = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM `Movies Table` WHERE category=? AND isFavorite IS NOT ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateMovieIsFavorite = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE `Movies Table` SET isFavorite=? WHERE cacheId=?";
        return _query;
      }
    };
  }

  @Override
  public Object saveMovies(final List<MovieEntity> movieEntities,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfMovieEntity.insert(movieEntities);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteMovies(final String category, final boolean isFavorite,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteMovies.acquire();
        int _argIndex = 1;
        if (category == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, category);
        }
        _argIndex = 2;
        final int _tmp;
        _tmp = isFavorite ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteMovies.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Object updateMovieIsFavorite(final int cacheId, final boolean isFavorite,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateMovieIsFavorite.acquire();
        int _argIndex = 1;
        final int _tmp;
        _tmp = isFavorite ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, cacheId);
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfUpdateMovieIsFavorite.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Flow<List<Movie>> getNowPlayingMovies(final String category) {
    final String _sql = "SELECT * FROM `Movies Table` WHERE category=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (category == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, category);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[]{"Movies Table"}, new Callable<List<Movie>>() {
      @Override
      public List<Movie> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfAdult = CursorUtil.getColumnIndexOrThrow(_cursor, "adult");
          final int _cursorIndexOfBackdropPath = CursorUtil.getColumnIndexOrThrow(_cursor, "backdropPath");
          final int _cursorIndexOfGenreIds = CursorUtil.getColumnIndexOrThrow(_cursor, "genreIds");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOriginalLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "originalLanguage");
          final int _cursorIndexOfOriginalTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "originalTitle");
          final int _cursorIndexOfOverview = CursorUtil.getColumnIndexOrThrow(_cursor, "overview");
          final int _cursorIndexOfPopularity = CursorUtil.getColumnIndexOrThrow(_cursor, "popularity");
          final int _cursorIndexOfPosterPath = CursorUtil.getColumnIndexOrThrow(_cursor, "posterPath");
          final int _cursorIndexOfReleaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "releaseDate");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfVideo = CursorUtil.getColumnIndexOrThrow(_cursor, "video");
          final int _cursorIndexOfVoteAverage = CursorUtil.getColumnIndexOrThrow(_cursor, "voteAverage");
          final int _cursorIndexOfVoteCount = CursorUtil.getColumnIndexOrThrow(_cursor, "voteCount");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfCacheId = CursorUtil.getColumnIndexOrThrow(_cursor, "cacheId");
          final List<Movie> _result = new ArrayList<Movie>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Movie _item;
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
            final List<Integer> _tmpGenreIds;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfGenreIds)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfGenreIds);
            }
            _tmpGenreIds = __genreIDEntityConverter.to(_tmp_1);
            final Integer _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getInt(_cursorIndexOfId);
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
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final Boolean _tmpIsFavorite;
            final Integer _tmp_3;
            if (_cursor.isNull(_cursorIndexOfIsFavorite)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getInt(_cursorIndexOfIsFavorite);
            }
            _tmpIsFavorite = _tmp_3 == null ? null : _tmp_3 != 0;
            final Integer _tmpCacheId;
            if (_cursor.isNull(_cursorIndexOfCacheId)) {
              _tmpCacheId = null;
            } else {
              _tmpCacheId = _cursor.getInt(_cursorIndexOfCacheId);
            }
            _item = new Movie(_tmpAdult,_tmpBackdropPath,_tmpGenreIds,_tmpId,_tmpOriginalLanguage,_tmpOriginalTitle,_tmpOverview,_tmpPopularity,_tmpPosterPath,_tmpReleaseDate,_tmpTitle,_tmpVideo,_tmpVoteAverage,_tmpVoteCount,_tmpCategory,_tmpIsFavorite,_tmpCacheId);
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

  @Override
  public PagingSource<Integer, Movie> getMovies(final String category) {
    final String _sql = "SELECT * FROM `Movies Table` WHERE category=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (category == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, category);
    }
    return new DataSource.Factory<Integer, Movie>() {
      @Override
      public LimitOffsetDataSource<Movie> create() {
        return new LimitOffsetDataSource<Movie>(__db, _statement, false, false , "Movies Table") {
          @Override
          protected List<Movie> convertRows(Cursor cursor) {
            final int _cursorIndexOfAdult = CursorUtil.getColumnIndexOrThrow(cursor, "adult");
            final int _cursorIndexOfBackdropPath = CursorUtil.getColumnIndexOrThrow(cursor, "backdropPath");
            final int _cursorIndexOfGenreIds = CursorUtil.getColumnIndexOrThrow(cursor, "genreIds");
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(cursor, "id");
            final int _cursorIndexOfOriginalLanguage = CursorUtil.getColumnIndexOrThrow(cursor, "originalLanguage");
            final int _cursorIndexOfOriginalTitle = CursorUtil.getColumnIndexOrThrow(cursor, "originalTitle");
            final int _cursorIndexOfOverview = CursorUtil.getColumnIndexOrThrow(cursor, "overview");
            final int _cursorIndexOfPopularity = CursorUtil.getColumnIndexOrThrow(cursor, "popularity");
            final int _cursorIndexOfPosterPath = CursorUtil.getColumnIndexOrThrow(cursor, "posterPath");
            final int _cursorIndexOfReleaseDate = CursorUtil.getColumnIndexOrThrow(cursor, "releaseDate");
            final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(cursor, "title");
            final int _cursorIndexOfVideo = CursorUtil.getColumnIndexOrThrow(cursor, "video");
            final int _cursorIndexOfVoteAverage = CursorUtil.getColumnIndexOrThrow(cursor, "voteAverage");
            final int _cursorIndexOfVoteCount = CursorUtil.getColumnIndexOrThrow(cursor, "voteCount");
            final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(cursor, "category");
            final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(cursor, "isFavorite");
            final int _cursorIndexOfCacheId = CursorUtil.getColumnIndexOrThrow(cursor, "cacheId");
            final List<Movie> _res = new ArrayList<Movie>(cursor.getCount());
            while(cursor.moveToNext()) {
              final Movie _item;
              final Boolean _tmpAdult;
              final Integer _tmp;
              if (cursor.isNull(_cursorIndexOfAdult)) {
                _tmp = null;
              } else {
                _tmp = cursor.getInt(_cursorIndexOfAdult);
              }
              _tmpAdult = _tmp == null ? null : _tmp != 0;
              final String _tmpBackdropPath;
              if (cursor.isNull(_cursorIndexOfBackdropPath)) {
                _tmpBackdropPath = null;
              } else {
                _tmpBackdropPath = cursor.getString(_cursorIndexOfBackdropPath);
              }
              final List<Integer> _tmpGenreIds;
              final String _tmp_1;
              if (cursor.isNull(_cursorIndexOfGenreIds)) {
                _tmp_1 = null;
              } else {
                _tmp_1 = cursor.getString(_cursorIndexOfGenreIds);
              }
              _tmpGenreIds = __genreIDEntityConverter.to(_tmp_1);
              final Integer _tmpId;
              if (cursor.isNull(_cursorIndexOfId)) {
                _tmpId = null;
              } else {
                _tmpId = cursor.getInt(_cursorIndexOfId);
              }
              final String _tmpOriginalLanguage;
              if (cursor.isNull(_cursorIndexOfOriginalLanguage)) {
                _tmpOriginalLanguage = null;
              } else {
                _tmpOriginalLanguage = cursor.getString(_cursorIndexOfOriginalLanguage);
              }
              final String _tmpOriginalTitle;
              if (cursor.isNull(_cursorIndexOfOriginalTitle)) {
                _tmpOriginalTitle = null;
              } else {
                _tmpOriginalTitle = cursor.getString(_cursorIndexOfOriginalTitle);
              }
              final String _tmpOverview;
              if (cursor.isNull(_cursorIndexOfOverview)) {
                _tmpOverview = null;
              } else {
                _tmpOverview = cursor.getString(_cursorIndexOfOverview);
              }
              final Double _tmpPopularity;
              if (cursor.isNull(_cursorIndexOfPopularity)) {
                _tmpPopularity = null;
              } else {
                _tmpPopularity = cursor.getDouble(_cursorIndexOfPopularity);
              }
              final String _tmpPosterPath;
              if (cursor.isNull(_cursorIndexOfPosterPath)) {
                _tmpPosterPath = null;
              } else {
                _tmpPosterPath = cursor.getString(_cursorIndexOfPosterPath);
              }
              final String _tmpReleaseDate;
              if (cursor.isNull(_cursorIndexOfReleaseDate)) {
                _tmpReleaseDate = null;
              } else {
                _tmpReleaseDate = cursor.getString(_cursorIndexOfReleaseDate);
              }
              final String _tmpTitle;
              if (cursor.isNull(_cursorIndexOfTitle)) {
                _tmpTitle = null;
              } else {
                _tmpTitle = cursor.getString(_cursorIndexOfTitle);
              }
              final Boolean _tmpVideo;
              final Integer _tmp_2;
              if (cursor.isNull(_cursorIndexOfVideo)) {
                _tmp_2 = null;
              } else {
                _tmp_2 = cursor.getInt(_cursorIndexOfVideo);
              }
              _tmpVideo = _tmp_2 == null ? null : _tmp_2 != 0;
              final Double _tmpVoteAverage;
              if (cursor.isNull(_cursorIndexOfVoteAverage)) {
                _tmpVoteAverage = null;
              } else {
                _tmpVoteAverage = cursor.getDouble(_cursorIndexOfVoteAverage);
              }
              final Integer _tmpVoteCount;
              if (cursor.isNull(_cursorIndexOfVoteCount)) {
                _tmpVoteCount = null;
              } else {
                _tmpVoteCount = cursor.getInt(_cursorIndexOfVoteCount);
              }
              final String _tmpCategory;
              if (cursor.isNull(_cursorIndexOfCategory)) {
                _tmpCategory = null;
              } else {
                _tmpCategory = cursor.getString(_cursorIndexOfCategory);
              }
              final Boolean _tmpIsFavorite;
              final Integer _tmp_3;
              if (cursor.isNull(_cursorIndexOfIsFavorite)) {
                _tmp_3 = null;
              } else {
                _tmp_3 = cursor.getInt(_cursorIndexOfIsFavorite);
              }
              _tmpIsFavorite = _tmp_3 == null ? null : _tmp_3 != 0;
              final Integer _tmpCacheId;
              if (cursor.isNull(_cursorIndexOfCacheId)) {
                _tmpCacheId = null;
              } else {
                _tmpCacheId = cursor.getInt(_cursorIndexOfCacheId);
              }
              _item = new Movie(_tmpAdult,_tmpBackdropPath,_tmpGenreIds,_tmpId,_tmpOriginalLanguage,_tmpOriginalTitle,_tmpOverview,_tmpPopularity,_tmpPosterPath,_tmpReleaseDate,_tmpTitle,_tmpVideo,_tmpVoteAverage,_tmpVoteCount,_tmpCategory,_tmpIsFavorite,_tmpCacheId);
              _res.add(_item);
            }
            return _res;
          }
        };
      }
    }.asPagingSourceFactory().invoke();
  }

  @Override
  public Object isCategoryCacheAvailable(final String category,
      final Continuation<? super Integer> continuation) {
    final String _sql = "SELECT COUNT(*) FROM `Movies Table` WHERE category=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (category == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, category);
    }
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

  @Override
  public Flow<List<MovieEntity>> getFavoriteMovies(final boolean isFavorite) {
    final String _sql = "SELECT * FROM `Movies Table` WHERE isFavorite=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final int _tmp;
    _tmp = isFavorite ? 1 : 0;
    _statement.bindLong(_argIndex, _tmp);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"Movies Table"}, new Callable<List<MovieEntity>>() {
      @Override
      public List<MovieEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfAdult = CursorUtil.getColumnIndexOrThrow(_cursor, "adult");
          final int _cursorIndexOfBackdropPath = CursorUtil.getColumnIndexOrThrow(_cursor, "backdropPath");
          final int _cursorIndexOfGenreIds = CursorUtil.getColumnIndexOrThrow(_cursor, "genreIds");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOriginalLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "originalLanguage");
          final int _cursorIndexOfOriginalTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "originalTitle");
          final int _cursorIndexOfOverview = CursorUtil.getColumnIndexOrThrow(_cursor, "overview");
          final int _cursorIndexOfPopularity = CursorUtil.getColumnIndexOrThrow(_cursor, "popularity");
          final int _cursorIndexOfPosterPath = CursorUtil.getColumnIndexOrThrow(_cursor, "posterPath");
          final int _cursorIndexOfReleaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "releaseDate");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfVideo = CursorUtil.getColumnIndexOrThrow(_cursor, "video");
          final int _cursorIndexOfVoteAverage = CursorUtil.getColumnIndexOrThrow(_cursor, "voteAverage");
          final int _cursorIndexOfVoteCount = CursorUtil.getColumnIndexOrThrow(_cursor, "voteCount");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfCacheId = CursorUtil.getColumnIndexOrThrow(_cursor, "cacheId");
          final List<MovieEntity> _result = new ArrayList<MovieEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final MovieEntity _item;
            final Boolean _tmpAdult;
            final Integer _tmp_1;
            if (_cursor.isNull(_cursorIndexOfAdult)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getInt(_cursorIndexOfAdult);
            }
            _tmpAdult = _tmp_1 == null ? null : _tmp_1 != 0;
            final String _tmpBackdropPath;
            if (_cursor.isNull(_cursorIndexOfBackdropPath)) {
              _tmpBackdropPath = null;
            } else {
              _tmpBackdropPath = _cursor.getString(_cursorIndexOfBackdropPath);
            }
            final List<Integer> _tmpGenreIds;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfGenreIds)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfGenreIds);
            }
            _tmpGenreIds = __genreIDEntityConverter.to(_tmp_2);
            final Integer _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getInt(_cursorIndexOfId);
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
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final boolean _tmpIsFavorite;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_4 != 0;
            final int _tmpCacheId;
            _tmpCacheId = _cursor.getInt(_cursorIndexOfCacheId);
            _item = new MovieEntity(_tmpAdult,_tmpBackdropPath,_tmpGenreIds,_tmpId,_tmpOriginalLanguage,_tmpOriginalTitle,_tmpOverview,_tmpPopularity,_tmpPosterPath,_tmpReleaseDate,_tmpTitle,_tmpVideo,_tmpVoteAverage,_tmpVoteCount,_tmpCategory,_tmpIsFavorite,_tmpCacheId);
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

  @Override
  public Flow<Boolean> isMovieFavorite(final int movieId) {
    final String _sql = "SELECT isFavorite FROM `Movies Table` WHERE id=? AND isFavorite = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, movieId);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"Movies Table"}, new Callable<Boolean>() {
      @Override
      public Boolean call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Boolean _result;
          if(_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp == null ? null : _tmp != 0;
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

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
