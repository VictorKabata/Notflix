package com.vickikbt.cache;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.vickikbt.cache.daos.CastDao;
import com.vickikbt.cache.daos.CastDao_Impl;
import com.vickikbt.cache.daos.FavoritesDao;
import com.vickikbt.cache.daos.FavoritesDao_Impl;
import com.vickikbt.cache.daos.MovieDetailsDao;
import com.vickikbt.cache.daos.MovieDetailsDao_Impl;
import com.vickikbt.cache.daos.PopularShowsDao;
import com.vickikbt.cache.daos.PopularShowsDao_Impl;
import com.vickikbt.cache.daos.UpcomingShowsDao;
import com.vickikbt.cache.daos.UpcomingShowsDao_Impl;
import com.vickikbt.cache.daos.VideosDao;
import com.vickikbt.cache.daos.VideosDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile PopularShowsDao _popularShowsDao;

  private volatile UpcomingShowsDao _upcomingShowsDao;

  private volatile MovieDetailsDao _movieDetailsDao;

  private volatile CastDao _castDao;

  private volatile VideosDao _videosDao;

  private volatile FavoritesDao _favoritesDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Popular_Result_Table` (`Page` INTEGER, `Movies` TEXT, `Total_Pages` INTEGER NOT NULL, `Total_Results` INTEGER, PRIMARY KEY(`Total_Pages`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Movies Table` (`Adult` INTEGER, `Backdrop_Path` TEXT, `Genre_IDs` TEXT, `ID` INTEGER NOT NULL, `Original_Language` TEXT, `Original_Title` TEXT, `Overview` TEXT, `Popularity` REAL, `Poster_Path` TEXT, `Release_Date` TEXT, `Title` TEXT, `Video` INTEGER, `Vote_Average` REAL, `Vote_Count` INTEGER, PRIMARY KEY(`ID`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Upcoming_Result_Table` (`Page` INTEGER, `Movies` TEXT, `Total_Page` INTEGER NOT NULL, `Total_Results` INTEGER, `Upcoming_Datesmaximum` TEXT, `Upcoming_Datesminimum` TEXT, PRIMARY KEY(`Total_Page`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Movie_Details_Table` (`Adult` INTEGER, `Backdrop_Path` TEXT, `Budget` INTEGER, `Genres` TEXT, `Homepage` TEXT, `ID` INTEGER NOT NULL, `IMDB_ID` TEXT, `Original_Language` TEXT, `Original_Title` TEXT, `Overview` TEXT, `Popularity` REAL, `Poster_Path` TEXT, `Production_Companies` TEXT, `Production_Country` TEXT, `Release Date` TEXT, `Revenue` INTEGER, `Runtime` INTEGER, `Spoken_Languages` TEXT, `Status` TEXT, `Tagline` TEXT, `Title` TEXT, `Video` INTEGER, `Vote_Average` REAL, `Vote_Count` INTEGER, PRIMARY KEY(`ID`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Cast_Table` (`Cast` TEXT, `ID` INTEGER NOT NULL, PRIMARY KEY(`ID`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Videos_Table` (`ID` INTEGER NOT NULL, `Video Items` TEXT, PRIMARY KEY(`ID`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Favorite Movies Table` (`Adult` INTEGER, `Backdrop_Path` TEXT, `Genre_IDs` TEXT, `ID` INTEGER NOT NULL, `Original_Language` TEXT, `Original_Title` TEXT, `Overview` TEXT, `Popularity` REAL, `Poster_Path` TEXT, `Release_Date` TEXT, `Title` TEXT, `Video` INTEGER, `Vote_Average` REAL, `Vote_Count` INTEGER, PRIMARY KEY(`ID`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '95dcb7b795a831dc70829463bd6aab0a')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Popular_Result_Table`");
        _db.execSQL("DROP TABLE IF EXISTS `Movies Table`");
        _db.execSQL("DROP TABLE IF EXISTS `Upcoming_Result_Table`");
        _db.execSQL("DROP TABLE IF EXISTS `Movie_Details_Table`");
        _db.execSQL("DROP TABLE IF EXISTS `Cast_Table`");
        _db.execSQL("DROP TABLE IF EXISTS `Videos_Table`");
        _db.execSQL("DROP TABLE IF EXISTS `Favorite Movies Table`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsPopularResultTable = new HashMap<String, TableInfo.Column>(4);
        _columnsPopularResultTable.put("Page", new TableInfo.Column("Page", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPopularResultTable.put("Movies", new TableInfo.Column("Movies", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPopularResultTable.put("Total_Pages", new TableInfo.Column("Total_Pages", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPopularResultTable.put("Total_Results", new TableInfo.Column("Total_Results", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPopularResultTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPopularResultTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPopularResultTable = new TableInfo("Popular_Result_Table", _columnsPopularResultTable, _foreignKeysPopularResultTable, _indicesPopularResultTable);
        final TableInfo _existingPopularResultTable = TableInfo.read(_db, "Popular_Result_Table");
        if (! _infoPopularResultTable.equals(_existingPopularResultTable)) {
          return new RoomOpenHelper.ValidationResult(false, "Popular_Result_Table(com.vickikbt.cache.models.PopularResultEntity).\n"
                  + " Expected:\n" + _infoPopularResultTable + "\n"
                  + " Found:\n" + _existingPopularResultTable);
        }
        final HashMap<String, TableInfo.Column> _columnsMoviesTable = new HashMap<String, TableInfo.Column>(14);
        _columnsMoviesTable.put("Adult", new TableInfo.Column("Adult", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoviesTable.put("Backdrop_Path", new TableInfo.Column("Backdrop_Path", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoviesTable.put("Genre_IDs", new TableInfo.Column("Genre_IDs", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoviesTable.put("ID", new TableInfo.Column("ID", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoviesTable.put("Original_Language", new TableInfo.Column("Original_Language", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoviesTable.put("Original_Title", new TableInfo.Column("Original_Title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoviesTable.put("Overview", new TableInfo.Column("Overview", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoviesTable.put("Popularity", new TableInfo.Column("Popularity", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoviesTable.put("Poster_Path", new TableInfo.Column("Poster_Path", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoviesTable.put("Release_Date", new TableInfo.Column("Release_Date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoviesTable.put("Title", new TableInfo.Column("Title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoviesTable.put("Video", new TableInfo.Column("Video", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoviesTable.put("Vote_Average", new TableInfo.Column("Vote_Average", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoviesTable.put("Vote_Count", new TableInfo.Column("Vote_Count", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMoviesTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMoviesTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMoviesTable = new TableInfo("Movies Table", _columnsMoviesTable, _foreignKeysMoviesTable, _indicesMoviesTable);
        final TableInfo _existingMoviesTable = TableInfo.read(_db, "Movies Table");
        if (! _infoMoviesTable.equals(_existingMoviesTable)) {
          return new RoomOpenHelper.ValidationResult(false, "Movies Table(com.vickikbt.cache.models.MovieEntity).\n"
                  + " Expected:\n" + _infoMoviesTable + "\n"
                  + " Found:\n" + _existingMoviesTable);
        }
        final HashMap<String, TableInfo.Column> _columnsUpcomingResultTable = new HashMap<String, TableInfo.Column>(6);
        _columnsUpcomingResultTable.put("Page", new TableInfo.Column("Page", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUpcomingResultTable.put("Movies", new TableInfo.Column("Movies", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUpcomingResultTable.put("Total_Page", new TableInfo.Column("Total_Page", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUpcomingResultTable.put("Total_Results", new TableInfo.Column("Total_Results", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUpcomingResultTable.put("Upcoming_Datesmaximum", new TableInfo.Column("Upcoming_Datesmaximum", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUpcomingResultTable.put("Upcoming_Datesminimum", new TableInfo.Column("Upcoming_Datesminimum", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUpcomingResultTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUpcomingResultTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUpcomingResultTable = new TableInfo("Upcoming_Result_Table", _columnsUpcomingResultTable, _foreignKeysUpcomingResultTable, _indicesUpcomingResultTable);
        final TableInfo _existingUpcomingResultTable = TableInfo.read(_db, "Upcoming_Result_Table");
        if (! _infoUpcomingResultTable.equals(_existingUpcomingResultTable)) {
          return new RoomOpenHelper.ValidationResult(false, "Upcoming_Result_Table(com.vickikbt.cache.models.UpcomingResultEntity).\n"
                  + " Expected:\n" + _infoUpcomingResultTable + "\n"
                  + " Found:\n" + _existingUpcomingResultTable);
        }
        final HashMap<String, TableInfo.Column> _columnsMovieDetailsTable = new HashMap<String, TableInfo.Column>(24);
        _columnsMovieDetailsTable.put("Adult", new TableInfo.Column("Adult", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("Backdrop_Path", new TableInfo.Column("Backdrop_Path", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("Budget", new TableInfo.Column("Budget", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("Genres", new TableInfo.Column("Genres", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("Homepage", new TableInfo.Column("Homepage", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("ID", new TableInfo.Column("ID", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("IMDB_ID", new TableInfo.Column("IMDB_ID", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("Original_Language", new TableInfo.Column("Original_Language", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("Original_Title", new TableInfo.Column("Original_Title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("Overview", new TableInfo.Column("Overview", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("Popularity", new TableInfo.Column("Popularity", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("Poster_Path", new TableInfo.Column("Poster_Path", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("Production_Companies", new TableInfo.Column("Production_Companies", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("Production_Country", new TableInfo.Column("Production_Country", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("Release Date", new TableInfo.Column("Release Date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("Revenue", new TableInfo.Column("Revenue", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("Runtime", new TableInfo.Column("Runtime", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("Spoken_Languages", new TableInfo.Column("Spoken_Languages", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("Status", new TableInfo.Column("Status", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("Tagline", new TableInfo.Column("Tagline", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("Title", new TableInfo.Column("Title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("Video", new TableInfo.Column("Video", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("Vote_Average", new TableInfo.Column("Vote_Average", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("Vote_Count", new TableInfo.Column("Vote_Count", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMovieDetailsTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMovieDetailsTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMovieDetailsTable = new TableInfo("Movie_Details_Table", _columnsMovieDetailsTable, _foreignKeysMovieDetailsTable, _indicesMovieDetailsTable);
        final TableInfo _existingMovieDetailsTable = TableInfo.read(_db, "Movie_Details_Table");
        if (! _infoMovieDetailsTable.equals(_existingMovieDetailsTable)) {
          return new RoomOpenHelper.ValidationResult(false, "Movie_Details_Table(com.vickikbt.cache.models.MovieDetailsEntity).\n"
                  + " Expected:\n" + _infoMovieDetailsTable + "\n"
                  + " Found:\n" + _existingMovieDetailsTable);
        }
        final HashMap<String, TableInfo.Column> _columnsCastTable = new HashMap<String, TableInfo.Column>(2);
        _columnsCastTable.put("Cast", new TableInfo.Column("Cast", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCastTable.put("ID", new TableInfo.Column("ID", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCastTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCastTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCastTable = new TableInfo("Cast_Table", _columnsCastTable, _foreignKeysCastTable, _indicesCastTable);
        final TableInfo _existingCastTable = TableInfo.read(_db, "Cast_Table");
        if (! _infoCastTable.equals(_existingCastTable)) {
          return new RoomOpenHelper.ValidationResult(false, "Cast_Table(com.vickikbt.cache.models.CastEntity).\n"
                  + " Expected:\n" + _infoCastTable + "\n"
                  + " Found:\n" + _existingCastTable);
        }
        final HashMap<String, TableInfo.Column> _columnsVideosTable = new HashMap<String, TableInfo.Column>(2);
        _columnsVideosTable.put("ID", new TableInfo.Column("ID", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVideosTable.put("Video Items", new TableInfo.Column("Video Items", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysVideosTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesVideosTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoVideosTable = new TableInfo("Videos_Table", _columnsVideosTable, _foreignKeysVideosTable, _indicesVideosTable);
        final TableInfo _existingVideosTable = TableInfo.read(_db, "Videos_Table");
        if (! _infoVideosTable.equals(_existingVideosTable)) {
          return new RoomOpenHelper.ValidationResult(false, "Videos_Table(com.vickikbt.cache.models.VideoEntity).\n"
                  + " Expected:\n" + _infoVideosTable + "\n"
                  + " Found:\n" + _existingVideosTable);
        }
        final HashMap<String, TableInfo.Column> _columnsFavoriteMoviesTable = new HashMap<String, TableInfo.Column>(14);
        _columnsFavoriteMoviesTable.put("Adult", new TableInfo.Column("Adult", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavoriteMoviesTable.put("Backdrop_Path", new TableInfo.Column("Backdrop_Path", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavoriteMoviesTable.put("Genre_IDs", new TableInfo.Column("Genre_IDs", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavoriteMoviesTable.put("ID", new TableInfo.Column("ID", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavoriteMoviesTable.put("Original_Language", new TableInfo.Column("Original_Language", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavoriteMoviesTable.put("Original_Title", new TableInfo.Column("Original_Title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavoriteMoviesTable.put("Overview", new TableInfo.Column("Overview", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavoriteMoviesTable.put("Popularity", new TableInfo.Column("Popularity", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavoriteMoviesTable.put("Poster_Path", new TableInfo.Column("Poster_Path", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavoriteMoviesTable.put("Release_Date", new TableInfo.Column("Release_Date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavoriteMoviesTable.put("Title", new TableInfo.Column("Title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavoriteMoviesTable.put("Video", new TableInfo.Column("Video", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavoriteMoviesTable.put("Vote_Average", new TableInfo.Column("Vote_Average", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavoriteMoviesTable.put("Vote_Count", new TableInfo.Column("Vote_Count", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFavoriteMoviesTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFavoriteMoviesTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFavoriteMoviesTable = new TableInfo("Favorite Movies Table", _columnsFavoriteMoviesTable, _foreignKeysFavoriteMoviesTable, _indicesFavoriteMoviesTable);
        final TableInfo _existingFavoriteMoviesTable = TableInfo.read(_db, "Favorite Movies Table");
        if (! _infoFavoriteMoviesTable.equals(_existingFavoriteMoviesTable)) {
          return new RoomOpenHelper.ValidationResult(false, "Favorite Movies Table(com.vickikbt.cache.models.FavoriteMovieEntity).\n"
                  + " Expected:\n" + _infoFavoriteMoviesTable + "\n"
                  + " Found:\n" + _existingFavoriteMoviesTable);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "95dcb7b795a831dc70829463bd6aab0a", "d0556f78645e275fb491b3641355963b");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "Popular_Result_Table","Movies Table","Upcoming_Result_Table","Movie_Details_Table","Cast_Table","Videos_Table","Favorite Movies Table");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `Popular_Result_Table`");
      _db.execSQL("DELETE FROM `Movies Table`");
      _db.execSQL("DELETE FROM `Upcoming_Result_Table`");
      _db.execSQL("DELETE FROM `Movie_Details_Table`");
      _db.execSQL("DELETE FROM `Cast_Table`");
      _db.execSQL("DELETE FROM `Videos_Table`");
      _db.execSQL("DELETE FROM `Favorite Movies Table`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(PopularShowsDao.class, PopularShowsDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(UpcomingShowsDao.class, UpcomingShowsDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(MovieDetailsDao.class, MovieDetailsDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CastDao.class, CastDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(VideosDao.class, VideosDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(FavoritesDao.class, FavoritesDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public PopularShowsDao popularShowsDao() {
    if (_popularShowsDao != null) {
      return _popularShowsDao;
    } else {
      synchronized(this) {
        if(_popularShowsDao == null) {
          _popularShowsDao = new PopularShowsDao_Impl(this);
        }
        return _popularShowsDao;
      }
    }
  }

  @Override
  public UpcomingShowsDao upcomingShowsDao() {
    if (_upcomingShowsDao != null) {
      return _upcomingShowsDao;
    } else {
      synchronized(this) {
        if(_upcomingShowsDao == null) {
          _upcomingShowsDao = new UpcomingShowsDao_Impl(this);
        }
        return _upcomingShowsDao;
      }
    }
  }

  @Override
  public MovieDetailsDao movieDetailsDao() {
    if (_movieDetailsDao != null) {
      return _movieDetailsDao;
    } else {
      synchronized(this) {
        if(_movieDetailsDao == null) {
          _movieDetailsDao = new MovieDetailsDao_Impl(this);
        }
        return _movieDetailsDao;
      }
    }
  }

  @Override
  public CastDao castDao() {
    if (_castDao != null) {
      return _castDao;
    } else {
      synchronized(this) {
        if(_castDao == null) {
          _castDao = new CastDao_Impl(this);
        }
        return _castDao;
      }
    }
  }

  @Override
  public VideosDao videosDao() {
    if (_videosDao != null) {
      return _videosDao;
    } else {
      synchronized(this) {
        if(_videosDao == null) {
          _videosDao = new VideosDao_Impl(this);
        }
        return _videosDao;
      }
    }
  }

  @Override
  public FavoritesDao favoritesDao() {
    if (_favoritesDao != null) {
      return _favoritesDao;
    } else {
      synchronized(this) {
        if(_favoritesDao == null) {
          _favoritesDao = new FavoritesDao_Impl(this);
        }
        return _favoritesDao;
      }
    }
  }
}
