package com.vickikbt.shared.`data`.cache.sqldelight.shared

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.TransacterImpl
import com.squareup.sqldelight.`internal`.copyOnWriteList
import com.squareup.sqldelight.db.SqlCursor
import com.squareup.sqldelight.db.SqlDriver
import com.vickikbt.shared.`data`.cache.sqldelight.AppDatabase
import com.vickikbt.shared.`data`.cache.sqldelight.AppDatabaseQueries
import com.vickikbt.shared.`data`.cache.sqldelight.IsMovieFavourite
import com.vickikbt.shared.`data`.cache.sqldelight.MovieDetailsEntity
import com.vickikbt.shared.`data`.cache.sqldelight.MovieEntity
import kotlin.Any
import kotlin.Boolean
import kotlin.Double
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Unit
import kotlin.collections.MutableList
import kotlin.reflect.KClass

internal val KClass<AppDatabase>.schema: SqlDriver.Schema
  get() = AppDatabaseImpl.Schema

internal fun KClass<AppDatabase>.newInstance(driver: SqlDriver): AppDatabase =
    AppDatabaseImpl(driver)

private class AppDatabaseImpl(
  driver: SqlDriver
) : TransacterImpl(driver), AppDatabase {
  public override val appDatabaseQueries: AppDatabaseQueriesImpl = AppDatabaseQueriesImpl(this,
      driver)

  public object Schema : SqlDriver.Schema {
    public override val version: Int
      get() = 1

    public override fun create(driver: SqlDriver): Unit {
      driver.execute(null, """
          |CREATE TABLE MovieEntity(
          |adult INTEGER DEFAULT NULL,
          |backdropPath TEXT DEFAULT NULL,
          |id INTEGER DEFAULT NULL,
          |originalLanguage TEXT DEFAULT NULL,
          |originalTitle TEXT DEFAULT NULL,
          |overview TEXT DEFAULT NULL,
          |popularity REAL DEFAULT NULL,
          |posterPath TEXT DEFAULT NULL,
          |releaseDate TEXT DEFAULT NULL,
          |title TEXT DEFAULT NULL,
          |video INTEGER DEFAULT NULL,
          |voteAverage REAL DEFAULT NULL,
          |voteCount INTEGER DEFAULT NULL,
          |category TEXT DEFAULT NULL,
          |isFavourite INTEGER DEFAULT FALSE,
          |cacheId INTEGER DEFAULT 0 PRIMARY KEY
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE MovieDetailsEntity(
          |adult INTEGER DEFAULT NULL,
          |backdropPath TEXT DEFAULT NULL,
          |homepage TEXT DEFAULT NULL,
          |id INTEGER UNIQUE NOT NULL PRIMARY KEY,
          |imdbId TEXT DEFAULT NULL,
          |originalLanguage TEXT DEFAULT NULL,
          |originalTitle TEXT DEFAULT NULL,
          |overview TEXT DEFAULT NULL,
          |popularity REAL DEFAULT NULL,
          |posterPath TEXT DEFAULT NULL,
          |releaseDate TEXT DEFAULT NULL,
          |runtime INTEGER DEFAULT NULL,
          |status TEXT DEFAULT NULL,
          |tagline TEXT DEFAULT NULL,
          |title TEXT DEFAULT NULL,
          |video INTEGER  DEFAULT NULL,
          |voteAverage REAL DEFAULT NULL,
          |voteCount INTEGER DEFAULT NULL
          |)
          """.trimMargin(), 0)
    }

    public override fun migrate(
      driver: SqlDriver,
      oldVersion: Int,
      newVersion: Int
    ): Unit {
    }
  }
}

private class AppDatabaseQueriesImpl(
  private val database: AppDatabaseImpl,
  private val driver: SqlDriver
) : TransacterImpl(driver), AppDatabaseQueries {
  internal val getNowPlayingMovies: MutableList<Query<*>> = copyOnWriteList()

  internal val getMovies: MutableList<Query<*>> = copyOnWriteList()

  internal val isCategoryCacheAvailable: MutableList<Query<*>> = copyOnWriteList()

  internal val getFavouriteMovies: MutableList<Query<*>> = copyOnWriteList()

  internal val isMovieFavourite: MutableList<Query<*>> = copyOnWriteList()

  internal val getMovieDetails: MutableList<Query<*>> = copyOnWriteList()

  internal val isMovieDetailsAvailable: MutableList<Query<*>> = copyOnWriteList()

  public override fun <T : Any> getNowPlayingMovies(category: String?, mapper: (
    adult: Boolean?,
    backdropPath: String?,
    id: Int?,
    originalLanguage: String?,
    originalTitle: String?,
    overview: String?,
    popularity: Double?,
    posterPath: String?,
    releaseDate: String?,
    title: String?,
    video: Boolean?,
    voteAverage: Double?,
    voteCount: Int?,
    category: String?,
    isFavourite: Boolean?,
    cacheId: Int
  ) -> T): Query<T> = GetNowPlayingMoviesQuery(category) { cursor ->
    mapper(
      cursor.getLong(0)?.let { it == 1L },
      cursor.getString(1),
      cursor.getLong(2)?.toInt(),
      cursor.getString(3),
      cursor.getString(4),
      cursor.getString(5),
      cursor.getDouble(6),
      cursor.getString(7),
      cursor.getString(8),
      cursor.getString(9),
      cursor.getLong(10)?.let { it == 1L },
      cursor.getDouble(11),
      cursor.getLong(12)?.toInt(),
      cursor.getString(13),
      cursor.getLong(14)?.let { it == 1L },
      cursor.getLong(15)!!.toInt()
    )
  }

  public override fun getNowPlayingMovies(category: String?): Query<MovieEntity> =
      getNowPlayingMovies(category) { adult, backdropPath, id, originalLanguage, originalTitle,
      overview, popularity, posterPath, releaseDate, title, video, voteAverage, voteCount,
      category_, isFavourite, cacheId ->
    MovieEntity(
      adult,
      backdropPath,
      id,
      originalLanguage,
      originalTitle,
      overview,
      popularity,
      posterPath,
      releaseDate,
      title,
      video,
      voteAverage,
      voteCount,
      category_,
      isFavourite,
      cacheId
    )
  }

  public override fun <T : Any> getMovies(category: String?, mapper: (
    adult: Boolean?,
    backdropPath: String?,
    id: Int?,
    originalLanguage: String?,
    originalTitle: String?,
    overview: String?,
    popularity: Double?,
    posterPath: String?,
    releaseDate: String?,
    title: String?,
    video: Boolean?,
    voteAverage: Double?,
    voteCount: Int?,
    category: String?,
    isFavourite: Boolean?,
    cacheId: Int
  ) -> T): Query<T> = GetMoviesQuery(category) { cursor ->
    mapper(
      cursor.getLong(0)?.let { it == 1L },
      cursor.getString(1),
      cursor.getLong(2)?.toInt(),
      cursor.getString(3),
      cursor.getString(4),
      cursor.getString(5),
      cursor.getDouble(6),
      cursor.getString(7),
      cursor.getString(8),
      cursor.getString(9),
      cursor.getLong(10)?.let { it == 1L },
      cursor.getDouble(11),
      cursor.getLong(12)?.toInt(),
      cursor.getString(13),
      cursor.getLong(14)?.let { it == 1L },
      cursor.getLong(15)!!.toInt()
    )
  }

  public override fun getMovies(category: String?): Query<MovieEntity> = getMovies(category) {
      adult, backdropPath, id, originalLanguage, originalTitle, overview, popularity, posterPath,
      releaseDate, title, video, voteAverage, voteCount, category_, isFavourite, cacheId ->
    MovieEntity(
      adult,
      backdropPath,
      id,
      originalLanguage,
      originalTitle,
      overview,
      popularity,
      posterPath,
      releaseDate,
      title,
      video,
      voteAverage,
      voteCount,
      category_,
      isFavourite,
      cacheId
    )
  }

  public override fun isCategoryCacheAvailable(category: String?): Query<Long> =
      IsCategoryCacheAvailableQuery(category) { cursor ->
    cursor.getLong(0)!!
  }

  public override fun <T : Any> getFavouriteMovies(mapper: (
    adult: Boolean?,
    backdropPath: String?,
    id: Int?,
    originalLanguage: String?,
    originalTitle: String?,
    overview: String?,
    popularity: Double?,
    posterPath: String?,
    releaseDate: String?,
    title: String?,
    video: Boolean?,
    voteAverage: Double?,
    voteCount: Int?,
    category: String?,
    isFavourite: Boolean?,
    cacheId: Int
  ) -> T): Query<T> = Query(-2013212026, getFavouriteMovies, driver, "AppDatabase.sq",
      "getFavouriteMovies", "SELECT * FROM MovieEntity WHERE isFavourite=TRUE") { cursor ->
    mapper(
      cursor.getLong(0)?.let { it == 1L },
      cursor.getString(1),
      cursor.getLong(2)?.toInt(),
      cursor.getString(3),
      cursor.getString(4),
      cursor.getString(5),
      cursor.getDouble(6),
      cursor.getString(7),
      cursor.getString(8),
      cursor.getString(9),
      cursor.getLong(10)?.let { it == 1L },
      cursor.getDouble(11),
      cursor.getLong(12)?.toInt(),
      cursor.getString(13),
      cursor.getLong(14)?.let { it == 1L },
      cursor.getLong(15)!!.toInt()
    )
  }

  public override fun getFavouriteMovies(): Query<MovieEntity> = getFavouriteMovies { adult,
      backdropPath, id, originalLanguage, originalTitle, overview, popularity, posterPath,
      releaseDate, title, video, voteAverage, voteCount, category, isFavourite, cacheId ->
    MovieEntity(
      adult,
      backdropPath,
      id,
      originalLanguage,
      originalTitle,
      overview,
      popularity,
      posterPath,
      releaseDate,
      title,
      video,
      voteAverage,
      voteCount,
      category,
      isFavourite,
      cacheId
    )
  }

  public override fun <T : Any> isMovieFavourite(id: Int?, mapper: (isFavourite: Boolean?) -> T):
      Query<T> = IsMovieFavouriteQuery(id) { cursor ->
    mapper(
      cursor.getLong(0)?.let { it == 1L }
    )
  }

  public override fun isMovieFavourite(id: Int?): Query<IsMovieFavourite> = isMovieFavourite(id) {
      isFavourite ->
    IsMovieFavourite(
      isFavourite
    )
  }

  public override fun <T : Any> getMovieDetails(id: Int, mapper: (
    adult: Boolean?,
    backdropPath: String?,
    homepage: String?,
    id: Int,
    imdbId: String?,
    originalLanguage: String?,
    originalTitle: String?,
    overview: String?,
    popularity: Double?,
    posterPath: String?,
    releaseDate: String?,
    runtime: Int?,
    status: String?,
    tagline: String?,
    title: String?,
    video: Boolean?,
    voteAverage: Double?,
    voteCount: Int?
  ) -> T): Query<T> = GetMovieDetailsQuery(id) { cursor ->
    mapper(
      cursor.getLong(0)?.let { it == 1L },
      cursor.getString(1),
      cursor.getString(2),
      cursor.getLong(3)!!.toInt(),
      cursor.getString(4),
      cursor.getString(5),
      cursor.getString(6),
      cursor.getString(7),
      cursor.getDouble(8),
      cursor.getString(9),
      cursor.getString(10),
      cursor.getLong(11)?.toInt(),
      cursor.getString(12),
      cursor.getString(13),
      cursor.getString(14),
      cursor.getLong(15)?.let { it == 1L },
      cursor.getDouble(16),
      cursor.getLong(17)?.toInt()
    )
  }

  public override fun getMovieDetails(id: Int): Query<MovieDetailsEntity> = getMovieDetails(id) {
      adult, backdropPath, homepage, id_, imdbId, originalLanguage, originalTitle, overview,
      popularity, posterPath, releaseDate, runtime, status, tagline, title, video, voteAverage,
      voteCount ->
    MovieDetailsEntity(
      adult,
      backdropPath,
      homepage,
      id_,
      imdbId,
      originalLanguage,
      originalTitle,
      overview,
      popularity,
      posterPath,
      releaseDate,
      runtime,
      status,
      tagline,
      title,
      video,
      voteAverage,
      voteCount
    )
  }

  public override fun isMovieDetailsAvailable(id: Int): Query<Long> =
      IsMovieDetailsAvailableQuery(id) { cursor ->
    cursor.getLong(0)!!
  }

  public override fun saveMovies(MovieEntity: MovieEntity): Unit {
    driver.execute(46047888, """
    |INSERT OR REPLACE INTO MovieEntity(adult, backdropPath,id,originalLanguage,originalTitle,overview,popularity,posterPath,releaseDate,title,video,voteAverage,voteCount,category,isFavourite,cacheId)
    |VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """.trimMargin(), 16) {
      bindLong(1, MovieEntity.adult?.let { if (it) 1L else 0L })
      bindString(2, MovieEntity.backdropPath)
      bindLong(3, MovieEntity.id?.let { it.toLong() })
      bindString(4, MovieEntity.originalLanguage)
      bindString(5, MovieEntity.originalTitle)
      bindString(6, MovieEntity.overview)
      bindDouble(7, MovieEntity.popularity)
      bindString(8, MovieEntity.posterPath)
      bindString(9, MovieEntity.releaseDate)
      bindString(10, MovieEntity.title)
      bindLong(11, MovieEntity.video?.let { if (it) 1L else 0L })
      bindDouble(12, MovieEntity.voteAverage)
      bindLong(13, MovieEntity.voteCount?.let { it.toLong() })
      bindString(14, MovieEntity.category)
      bindLong(15, MovieEntity.isFavourite?.let { if (it) 1L else 0L })
      bindLong(16, MovieEntity.cacheId.toLong())
    }
    notifyQueries(46047888, {database.appDatabaseQueries.getFavouriteMovies +
        database.appDatabaseQueries.isMovieFavourite + database.appDatabaseQueries.getMovies +
        database.appDatabaseQueries.isCategoryCacheAvailable +
        database.appDatabaseQueries.getNowPlayingMovies})
  }

  public override fun deleteMovies(category: String?): Unit {
    driver.execute(null,
        """DELETE FROM MovieEntity WHERE category${ if (category == null) " IS " else "=" }? AND isFavourite IS NOT TRUE""",
        1) {
      bindString(1, category)
    }
    notifyQueries(530640958, {database.appDatabaseQueries.getFavouriteMovies +
        database.appDatabaseQueries.isMovieFavourite + database.appDatabaseQueries.getMovies +
        database.appDatabaseQueries.isCategoryCacheAvailable +
        database.appDatabaseQueries.getNowPlayingMovies})
  }

  public override fun updateIsMovieFavorite(isFavourite: Boolean?, cacheId: Int): Unit {
    driver.execute(-138599831, """
    |UPDATE MovieEntity
    |SET `isFavourite`=? WHERE cacheId=?
    """.trimMargin(), 2) {
      bindLong(1, isFavourite?.let { if (it) 1L else 0L })
      bindLong(2, cacheId.toLong())
    }
    notifyQueries(-138599831, {database.appDatabaseQueries.getFavouriteMovies +
        database.appDatabaseQueries.isMovieFavourite + database.appDatabaseQueries.getMovies +
        database.appDatabaseQueries.isCategoryCacheAvailable +
        database.appDatabaseQueries.getNowPlayingMovies})
  }

  public override fun saveMovieDetails(MovieDetailsEntity: MovieDetailsEntity): Unit {
    driver.execute(-337419649, """
    |INSERT OR REPLACE INTO MovieDetailsEntity(adult, backdropPath,homepage,id,imdbId,originalLanguage,originalTitle,overview,popularity,posterPath,releaseDate,runtime,status,tagline,title,video,voteAverage,voteCount)
    |VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """.trimMargin(), 18) {
      bindLong(1, MovieDetailsEntity.adult?.let { if (it) 1L else 0L })
      bindString(2, MovieDetailsEntity.backdropPath)
      bindString(3, MovieDetailsEntity.homepage)
      bindLong(4, MovieDetailsEntity.id.toLong())
      bindString(5, MovieDetailsEntity.imdbId)
      bindString(6, MovieDetailsEntity.originalLanguage)
      bindString(7, MovieDetailsEntity.originalTitle)
      bindString(8, MovieDetailsEntity.overview)
      bindDouble(9, MovieDetailsEntity.popularity)
      bindString(10, MovieDetailsEntity.posterPath)
      bindString(11, MovieDetailsEntity.releaseDate)
      bindLong(12, MovieDetailsEntity.runtime?.let { it.toLong() })
      bindString(13, MovieDetailsEntity.status)
      bindString(14, MovieDetailsEntity.tagline)
      bindString(15, MovieDetailsEntity.title)
      bindLong(16, MovieDetailsEntity.video?.let { if (it) 1L else 0L })
      bindDouble(17, MovieDetailsEntity.voteAverage)
      bindLong(18, MovieDetailsEntity.voteCount?.let { it.toLong() })
    }
    notifyQueries(-337419649, {database.appDatabaseQueries.isMovieDetailsAvailable +
        database.appDatabaseQueries.getMovieDetails})
  }

  public override fun deleteAllMovieDetais(): Unit {
    driver.execute(-737358526, """DELETE FROM MovieDetailsEntity""", 0)
    notifyQueries(-737358526, {database.appDatabaseQueries.isMovieDetailsAvailable +
        database.appDatabaseQueries.getMovieDetails})
  }

  private inner class GetNowPlayingMoviesQuery<out T : Any>(
    public val category: String?,
    mapper: (SqlCursor) -> T
  ) : Query<T>(getNowPlayingMovies, mapper) {
    public override fun execute(): SqlCursor = driver.executeQuery(null,
        """SELECT * FROM MovieEntity WHERE category${ if (category == null) " IS " else "=" }?""",
        1) {
      bindString(1, category)
    }

    public override fun toString(): String = "AppDatabase.sq:getNowPlayingMovies"
  }

  private inner class GetMoviesQuery<out T : Any>(
    public val category: String?,
    mapper: (SqlCursor) -> T
  ) : Query<T>(getMovies, mapper) {
    public override fun execute(): SqlCursor = driver.executeQuery(null,
        """SELECT * FROM MovieEntity WHERE category${ if (category == null) " IS " else "=" }?""",
        1) {
      bindString(1, category)
    }

    public override fun toString(): String = "AppDatabase.sq:getMovies"
  }

  private inner class IsCategoryCacheAvailableQuery<out T : Any>(
    public val category: String?,
    mapper: (SqlCursor) -> T
  ) : Query<T>(isCategoryCacheAvailable, mapper) {
    public override fun execute(): SqlCursor = driver.executeQuery(null,
        """SELECT COUNT(*) FROM MovieEntity WHERE category${ if (category == null) " IS " else "=" }?""",
        1) {
      bindString(1, category)
    }

    public override fun toString(): String = "AppDatabase.sq:isCategoryCacheAvailable"
  }

  private inner class IsMovieFavouriteQuery<out T : Any>(
    public val id: Int?,
    mapper: (SqlCursor) -> T
  ) : Query<T>(isMovieFavourite, mapper) {
    public override fun execute(): SqlCursor = driver.executeQuery(null,
        """SELECT isFavourite FROM MovieEntity WHERE id${ if (id == null) " IS " else "=" }? AND isFavourite=TRUE""",
        1) {
      bindLong(1, id?.let { it.toLong() })
    }

    public override fun toString(): String = "AppDatabase.sq:isMovieFavourite"
  }

  private inner class GetMovieDetailsQuery<out T : Any>(
    public val id: Int,
    mapper: (SqlCursor) -> T
  ) : Query<T>(getMovieDetails, mapper) {
    public override fun execute(): SqlCursor = driver.executeQuery(-1867289096,
        """SELECT * FROM MovieDetailsEntity WHERE id=?""", 1) {
      bindLong(1, id.toLong())
    }

    public override fun toString(): String = "AppDatabase.sq:getMovieDetails"
  }

  private inner class IsMovieDetailsAvailableQuery<out T : Any>(
    public val id: Int,
    mapper: (SqlCursor) -> T
  ) : Query<T>(isMovieDetailsAvailable, mapper) {
    public override fun execute(): SqlCursor = driver.executeQuery(-2118992515,
        """SELECT COUNT(*) FROM MovieDetailsEntity WHERE id=?""", 1) {
      bindLong(1, id.toLong())
    }

    public override fun toString(): String = "AppDatabase.sq:isMovieDetailsAvailable"
  }
}
