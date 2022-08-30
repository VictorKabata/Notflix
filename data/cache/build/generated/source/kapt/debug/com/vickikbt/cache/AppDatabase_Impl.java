package com.vickikbt.cache;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
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
import com.vickikbt.cache.daos.MovieDetailsDao;
import com.vickikbt.cache.daos.MovieDetailsDao_Impl;
import com.vickikbt.cache.daos.MoviesDao;
import com.vickikbt.cache.daos.MoviesDao_Impl;
import com.vickikbt.cache.daos.RemoteKeyDao;
import com.vickikbt.cache.daos.RemoteKeyDao_Impl;
import com.vickikbt.cache.daos.VideosDao;
import com.vickikbt.cache.daos.VideosDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile MoviesDao _moviesDao;

  private volatile RemoteKeyDao _remoteKeyDao;

  private volatile MovieDetailsDao _movieDetailsDao;

  private volatile CastDao _castDao;

  private volatile VideosDao _videosDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Movies Table` (`adult` INTEGER, `backdropPath` TEXT, `genreIds` TEXT, `id` INTEGER, `originalLanguage` TEXT, `originalTitle` TEXT, `overview` TEXT, `popularity` REAL, `posterPath` TEXT, `releaseDate` TEXT, `title` TEXT, `video` INTEGER, `voteAverage` REAL, `voteCount` INTEGER, `category` TEXT, `isFavorite` INTEGER NOT NULL, `cacheId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Remote_Key_Table` (`Movie_ID` INTEGER NOT NULL, `Previous_Key` INTEGER, `Next_Key` INTEGER, PRIMARY KEY(`Movie_ID`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Movie Details Table` (`adult` INTEGER, `backdropPath` TEXT, `genres` TEXT, `homepage` TEXT, `id` INTEGER, `imdbId` TEXT, `originalLanguage` TEXT, `originalTitle` TEXT, `overview` TEXT, `popularity` REAL, `posterPath` TEXT, `releaseDate` TEXT, `runtime` INTEGER, `spokenLanguages` TEXT, `status` TEXT, `tagline` TEXT, `title` TEXT, `video` INTEGER, `voteAverage` REAL, `voteCount` INTEGER, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Casts Table` (`actor` TEXT, `id` INTEGER, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Movie Videos Table` (`id` INTEGER, `videos` TEXT, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '1c275903d09ff5a76beabaea2a515e6c')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Movies Table`");
        _db.execSQL("DROP TABLE IF EXISTS `Remote_Key_Table`");
        _db.execSQL("DROP TABLE IF EXISTS `Movie Details Table`");
        _db.execSQL("DROP TABLE IF EXISTS `Casts Table`");
        _db.execSQL("DROP TABLE IF EXISTS `Movie Videos Table`");
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
        final HashMap<String, TableInfo.Column> _columnsMoviesTable = new HashMap<String, TableInfo.Column>(17);
        _columnsMoviesTable.put("adult", new TableInfo.Column("adult", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoviesTable.put("backdropPath", new TableInfo.Column("backdropPath", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoviesTable.put("genreIds", new TableInfo.Column("genreIds", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoviesTable.put("id", new TableInfo.Column("id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoviesTable.put("originalLanguage", new TableInfo.Column("originalLanguage", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoviesTable.put("originalTitle", new TableInfo.Column("originalTitle", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoviesTable.put("overview", new TableInfo.Column("overview", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoviesTable.put("popularity", new TableInfo.Column("popularity", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoviesTable.put("posterPath", new TableInfo.Column("posterPath", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoviesTable.put("releaseDate", new TableInfo.Column("releaseDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoviesTable.put("title", new TableInfo.Column("title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoviesTable.put("video", new TableInfo.Column("video", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoviesTable.put("voteAverage", new TableInfo.Column("voteAverage", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoviesTable.put("voteCount", new TableInfo.Column("voteCount", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoviesTable.put("category", new TableInfo.Column("category", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoviesTable.put("isFavorite", new TableInfo.Column("isFavorite", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMoviesTable.put("cacheId", new TableInfo.Column("cacheId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMoviesTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMoviesTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMoviesTable = new TableInfo("Movies Table", _columnsMoviesTable, _foreignKeysMoviesTable, _indicesMoviesTable);
        final TableInfo _existingMoviesTable = TableInfo.read(_db, "Movies Table");
        if (! _infoMoviesTable.equals(_existingMoviesTable)) {
          return new RoomOpenHelper.ValidationResult(false, "Movies Table(com.vickikbt.cache.models.MovieEntity).\n"
                  + " Expected:\n" + _infoMoviesTable + "\n"
                  + " Found:\n" + _existingMoviesTable);
        }
        final HashMap<String, TableInfo.Column> _columnsRemoteKeyTable = new HashMap<String, TableInfo.Column>(3);
        _columnsRemoteKeyTable.put("Movie_ID", new TableInfo.Column("Movie_ID", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRemoteKeyTable.put("Previous_Key", new TableInfo.Column("Previous_Key", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRemoteKeyTable.put("Next_Key", new TableInfo.Column("Next_Key", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRemoteKeyTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRemoteKeyTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRemoteKeyTable = new TableInfo("Remote_Key_Table", _columnsRemoteKeyTable, _foreignKeysRemoteKeyTable, _indicesRemoteKeyTable);
        final TableInfo _existingRemoteKeyTable = TableInfo.read(_db, "Remote_Key_Table");
        if (! _infoRemoteKeyTable.equals(_existingRemoteKeyTable)) {
          return new RoomOpenHelper.ValidationResult(false, "Remote_Key_Table(com.vickikbt.cache.models.RemoteKey).\n"
                  + " Expected:\n" + _infoRemoteKeyTable + "\n"
                  + " Found:\n" + _existingRemoteKeyTable);
        }
        final HashMap<String, TableInfo.Column> _columnsMovieDetailsTable = new HashMap<String, TableInfo.Column>(20);
        _columnsMovieDetailsTable.put("adult", new TableInfo.Column("adult", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("backdropPath", new TableInfo.Column("backdropPath", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("genres", new TableInfo.Column("genres", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("homepage", new TableInfo.Column("homepage", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("imdbId", new TableInfo.Column("imdbId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("originalLanguage", new TableInfo.Column("originalLanguage", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("originalTitle", new TableInfo.Column("originalTitle", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("overview", new TableInfo.Column("overview", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("popularity", new TableInfo.Column("popularity", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("posterPath", new TableInfo.Column("posterPath", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("releaseDate", new TableInfo.Column("releaseDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("runtime", new TableInfo.Column("runtime", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("spokenLanguages", new TableInfo.Column("spokenLanguages", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("status", new TableInfo.Column("status", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("tagline", new TableInfo.Column("tagline", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("title", new TableInfo.Column("title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("video", new TableInfo.Column("video", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("voteAverage", new TableInfo.Column("voteAverage", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieDetailsTable.put("voteCount", new TableInfo.Column("voteCount", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMovieDetailsTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMovieDetailsTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMovieDetailsTable = new TableInfo("Movie Details Table", _columnsMovieDetailsTable, _foreignKeysMovieDetailsTable, _indicesMovieDetailsTable);
        final TableInfo _existingMovieDetailsTable = TableInfo.read(_db, "Movie Details Table");
        if (! _infoMovieDetailsTable.equals(_existingMovieDetailsTable)) {
          return new RoomOpenHelper.ValidationResult(false, "Movie Details Table(com.vickikbt.cache.models.MovieDetailsEntity).\n"
                  + " Expected:\n" + _infoMovieDetailsTable + "\n"
                  + " Found:\n" + _existingMovieDetailsTable);
        }
        final HashMap<String, TableInfo.Column> _columnsCastsTable = new HashMap<String, TableInfo.Column>(2);
        _columnsCastsTable.put("actor", new TableInfo.Column("actor", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCastsTable.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCastsTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCastsTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCastsTable = new TableInfo("Casts Table", _columnsCastsTable, _foreignKeysCastsTable, _indicesCastsTable);
        final TableInfo _existingCastsTable = TableInfo.read(_db, "Casts Table");
        if (! _infoCastsTable.equals(_existingCastsTable)) {
          return new RoomOpenHelper.ValidationResult(false, "Casts Table(com.vickikbt.cache.models.CastEntity).\n"
                  + " Expected:\n" + _infoCastsTable + "\n"
                  + " Found:\n" + _existingCastsTable);
        }
        final HashMap<String, TableInfo.Column> _columnsMovieVideosTable = new HashMap<String, TableInfo.Column>(2);
        _columnsMovieVideosTable.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovieVideosTable.put("videos", new TableInfo.Column("videos", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMovieVideosTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMovieVideosTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMovieVideosTable = new TableInfo("Movie Videos Table", _columnsMovieVideosTable, _foreignKeysMovieVideosTable, _indicesMovieVideosTable);
        final TableInfo _existingMovieVideosTable = TableInfo.read(_db, "Movie Videos Table");
        if (! _infoMovieVideosTable.equals(_existingMovieVideosTable)) {
          return new RoomOpenHelper.ValidationResult(false, "Movie Videos Table(com.vickikbt.cache.models.MovieVideoEntity).\n"
                  + " Expected:\n" + _infoMovieVideosTable + "\n"
                  + " Found:\n" + _existingMovieVideosTable);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "1c275903d09ff5a76beabaea2a515e6c", "5fec686c6970d88e7562acb7822bfa8d");
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
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "Movies Table","Remote_Key_Table","Movie Details Table","Casts Table","Movie Videos Table");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `Movies Table`");
      _db.execSQL("DELETE FROM `Remote_Key_Table`");
      _db.execSQL("DELETE FROM `Movie Details Table`");
      _db.execSQL("DELETE FROM `Casts Table`");
      _db.execSQL("DELETE FROM `Movie Videos Table`");
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
    _typeConvertersMap.put(MoviesDao.class, MoviesDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(RemoteKeyDao.class, RemoteKeyDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(MovieDetailsDao.class, MovieDetailsDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CastDao.class, CastDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(VideosDao.class, VideosDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  protected Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  protected List<Migration> getAutoMigrations() {
    return Arrays.asList();
  }

  @Override
  public MoviesDao moviesDao() {
    if (_moviesDao != null) {
      return _moviesDao;
    } else {
      synchronized(this) {
        if(_moviesDao == null) {
          _moviesDao = new MoviesDao_Impl(this);
        }
        return _moviesDao;
      }
    }
  }

  @Override
  public RemoteKeyDao remoteKeyDao() {
    if (_remoteKeyDao != null) {
      return _remoteKeyDao;
    } else {
      synchronized(this) {
        if(_remoteKeyDao == null) {
          _remoteKeyDao = new RemoteKeyDao_Impl(this);
        }
        return _remoteKeyDao;
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
}
