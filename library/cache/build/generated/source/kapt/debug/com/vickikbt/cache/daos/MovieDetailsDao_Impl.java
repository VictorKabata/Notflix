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
import com.vickikbt.cache.converters.ProductionCompanyEntityConverter;
import com.vickikbt.cache.converters.ProductionCountryEntityConverter;
import com.vickikbt.cache.converters.SpokenLanguageEntityConverter;
import com.vickikbt.cache.models.GenreEntity;
import com.vickikbt.cache.models.MovieDetailsEntity;
import com.vickikbt.cache.models.ProductionCompanyEntity;
import com.vickikbt.cache.models.ProductionCountryEntity;
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

@SuppressWarnings({"unchecked", "deprecation"})
public final class MovieDetailsDao_Impl implements MovieDetailsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MovieDetailsEntity> __insertionAdapterOfMovieDetailsEntity;

  private final GenreEntityConverter __genreEntityConverter = new GenreEntityConverter();

  private final ProductionCompanyEntityConverter __productionCompanyEntityConverter = new ProductionCompanyEntityConverter();

  private final ProductionCountryEntityConverter __productionCountryEntityConverter = new ProductionCountryEntityConverter();

  private final SpokenLanguageEntityConverter __spokenLanguageEntityConverter = new SpokenLanguageEntityConverter();

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllMovieDetails;

  public MovieDetailsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMovieDetailsEntity = new EntityInsertionAdapter<MovieDetailsEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Movie_Details_Table` (`Adult`,`Backdrop_Path`,`Budget`,`Genres`,`Homepage`,`ID`,`IMDB_ID`,`Original_Language`,`Original_Title`,`Overview`,`Popularity`,`Poster_Path`,`Production_Companies`,`Production_Country`,`Release Date`,`Revenue`,`Runtime`,`Spoken_Languages`,`Status`,`Tagline`,`Title`,`Video`,`Vote_Average`,`Vote_Count`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
        if (value.getBudget() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, value.getBudget());
        }
        final String _tmp_1;
        _tmp_1 = __genreEntityConverter.from(value.getGenresEntity());
        if (_tmp_1 == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, _tmp_1);
        }
        if (value.getHomepage() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getHomepage());
        }
        stmt.bindLong(6, value.getId());
        if (value.getImdbId() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getImdbId());
        }
        if (value.getOriginalLanguage() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getOriginalLanguage());
        }
        if (value.getOriginalTitle() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getOriginalTitle());
        }
        if (value.getOverview() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getOverview());
        }
        if (value.getPopularity() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindDouble(11, value.getPopularity());
        }
        if (value.getPosterPath() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getPosterPath());
        }
        final String _tmp_2;
        _tmp_2 = __productionCompanyEntityConverter.from(value.getProductionCompaniesEntity());
        if (_tmp_2 == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, _tmp_2);
        }
        final String _tmp_3;
        _tmp_3 = __productionCountryEntityConverter.from(value.getProductionCountriesEntity());
        if (_tmp_3 == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, _tmp_3);
        }
        if (value.getReleaseDate() == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindString(15, value.getReleaseDate());
        }
        if (value.getRevenue() == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindLong(16, value.getRevenue());
        }
        if (value.getRuntime() == null) {
          stmt.bindNull(17);
        } else {
          stmt.bindLong(17, value.getRuntime());
        }
        final String _tmp_4;
        _tmp_4 = __spokenLanguageEntityConverter.from(value.getSpokenLanguagesEntity());
        if (_tmp_4 == null) {
          stmt.bindNull(18);
        } else {
          stmt.bindString(18, _tmp_4);
        }
        if (value.getStatus() == null) {
          stmt.bindNull(19);
        } else {
          stmt.bindString(19, value.getStatus());
        }
        if (value.getTagline() == null) {
          stmt.bindNull(20);
        } else {
          stmt.bindString(20, value.getTagline());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(21);
        } else {
          stmt.bindString(21, value.getTitle());
        }
        final Integer _tmp_5;
        _tmp_5 = value.getVideo() == null ? null : (value.getVideo() ? 1 : 0);
        if (_tmp_5 == null) {
          stmt.bindNull(22);
        } else {
          stmt.bindLong(22, _tmp_5);
        }
        if (value.getVoteAverage() == null) {
          stmt.bindNull(23);
        } else {
          stmt.bindDouble(23, value.getVoteAverage());
        }
        if (value.getVoteCount() == null) {
          stmt.bindNull(24);
        } else {
          stmt.bindLong(24, value.getVoteCount());
        }
      }
    };
    this.__preparedStmtOfDeleteAllMovieDetails = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Movie_Details_Table";
        return _query;
      }
    };
  }

  @Override
  public Object saveMovieDetails(final MovieDetailsEntity movieDetails,
      final Continuation<? super Unit> p1) {
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
    }, p1);
  }

  @Override
  public Object deleteAllMovieDetails(final Continuation<? super Unit> p0) {
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
    }, p0);
  }

  @Override
  public Object getMovieDetails(final int movieId,
      final Continuation<? super MovieDetailsEntity> p1) {
    final String _sql = "SELECT * FROM Movie_Details_Table WHERE ID=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, movieId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<MovieDetailsEntity>() {
      @Override
      public MovieDetailsEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfAdult = CursorUtil.getColumnIndexOrThrow(_cursor, "Adult");
          final int _cursorIndexOfBackdropPath = CursorUtil.getColumnIndexOrThrow(_cursor, "Backdrop_Path");
          final int _cursorIndexOfBudget = CursorUtil.getColumnIndexOrThrow(_cursor, "Budget");
          final int _cursorIndexOfGenresEntity = CursorUtil.getColumnIndexOrThrow(_cursor, "Genres");
          final int _cursorIndexOfHomepage = CursorUtil.getColumnIndexOrThrow(_cursor, "Homepage");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "ID");
          final int _cursorIndexOfImdbId = CursorUtil.getColumnIndexOrThrow(_cursor, "IMDB_ID");
          final int _cursorIndexOfOriginalLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "Original_Language");
          final int _cursorIndexOfOriginalTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "Original_Title");
          final int _cursorIndexOfOverview = CursorUtil.getColumnIndexOrThrow(_cursor, "Overview");
          final int _cursorIndexOfPopularity = CursorUtil.getColumnIndexOrThrow(_cursor, "Popularity");
          final int _cursorIndexOfPosterPath = CursorUtil.getColumnIndexOrThrow(_cursor, "Poster_Path");
          final int _cursorIndexOfProductionCompaniesEntity = CursorUtil.getColumnIndexOrThrow(_cursor, "Production_Companies");
          final int _cursorIndexOfProductionCountriesEntity = CursorUtil.getColumnIndexOrThrow(_cursor, "Production_Country");
          final int _cursorIndexOfReleaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "Release Date");
          final int _cursorIndexOfRevenue = CursorUtil.getColumnIndexOrThrow(_cursor, "Revenue");
          final int _cursorIndexOfRuntime = CursorUtil.getColumnIndexOrThrow(_cursor, "Runtime");
          final int _cursorIndexOfSpokenLanguagesEntity = CursorUtil.getColumnIndexOrThrow(_cursor, "Spoken_Languages");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "Status");
          final int _cursorIndexOfTagline = CursorUtil.getColumnIndexOrThrow(_cursor, "Tagline");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "Title");
          final int _cursorIndexOfVideo = CursorUtil.getColumnIndexOrThrow(_cursor, "Video");
          final int _cursorIndexOfVoteAverage = CursorUtil.getColumnIndexOrThrow(_cursor, "Vote_Average");
          final int _cursorIndexOfVoteCount = CursorUtil.getColumnIndexOrThrow(_cursor, "Vote_Count");
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
            final Integer _tmpBudget;
            if (_cursor.isNull(_cursorIndexOfBudget)) {
              _tmpBudget = null;
            } else {
              _tmpBudget = _cursor.getInt(_cursorIndexOfBudget);
            }
            final List<GenreEntity> _tmpGenresEntity;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfGenresEntity)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfGenresEntity);
            }
            _tmpGenresEntity = __genreEntityConverter.to(_tmp_1);
            final String _tmpHomepage;
            if (_cursor.isNull(_cursorIndexOfHomepage)) {
              _tmpHomepage = null;
            } else {
              _tmpHomepage = _cursor.getString(_cursorIndexOfHomepage);
            }
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
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
            final List<ProductionCompanyEntity> _tmpProductionCompaniesEntity;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfProductionCompaniesEntity)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfProductionCompaniesEntity);
            }
            _tmpProductionCompaniesEntity = __productionCompanyEntityConverter.to(_tmp_2);
            final List<ProductionCountryEntity> _tmpProductionCountriesEntity;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfProductionCountriesEntity)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfProductionCountriesEntity);
            }
            _tmpProductionCountriesEntity = __productionCountryEntityConverter.to(_tmp_3);
            final String _tmpReleaseDate;
            if (_cursor.isNull(_cursorIndexOfReleaseDate)) {
              _tmpReleaseDate = null;
            } else {
              _tmpReleaseDate = _cursor.getString(_cursorIndexOfReleaseDate);
            }
            final Integer _tmpRevenue;
            if (_cursor.isNull(_cursorIndexOfRevenue)) {
              _tmpRevenue = null;
            } else {
              _tmpRevenue = _cursor.getInt(_cursorIndexOfRevenue);
            }
            final Integer _tmpRuntime;
            if (_cursor.isNull(_cursorIndexOfRuntime)) {
              _tmpRuntime = null;
            } else {
              _tmpRuntime = _cursor.getInt(_cursorIndexOfRuntime);
            }
            final List<SpokenLanguageEntity> _tmpSpokenLanguagesEntity;
            final String _tmp_4;
            if (_cursor.isNull(_cursorIndexOfSpokenLanguagesEntity)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getString(_cursorIndexOfSpokenLanguagesEntity);
            }
            _tmpSpokenLanguagesEntity = __spokenLanguageEntityConverter.to(_tmp_4);
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
            final Integer _tmp_5;
            if (_cursor.isNull(_cursorIndexOfVideo)) {
              _tmp_5 = null;
            } else {
              _tmp_5 = _cursor.getInt(_cursorIndexOfVideo);
            }
            _tmpVideo = _tmp_5 == null ? null : _tmp_5 != 0;
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
            _result = new MovieDetailsEntity(_tmpAdult,_tmpBackdropPath,_tmpBudget,_tmpGenresEntity,_tmpHomepage,_tmpId,_tmpImdbId,_tmpOriginalLanguage,_tmpOriginalTitle,_tmpOverview,_tmpPopularity,_tmpPosterPath,_tmpProductionCompaniesEntity,_tmpProductionCountriesEntity,_tmpReleaseDate,_tmpRevenue,_tmpRuntime,_tmpSpokenLanguagesEntity,_tmpStatus,_tmpTagline,_tmpTitle,_tmpVideo,_tmpVoteAverage,_tmpVoteCount);
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
