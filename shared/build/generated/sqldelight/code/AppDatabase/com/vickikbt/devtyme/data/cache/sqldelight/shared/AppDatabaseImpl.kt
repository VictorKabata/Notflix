package com.vickikbt.devtyme.`data`.cache.sqldelight.shared

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.TransacterImpl
import com.squareup.sqldelight.`internal`.copyOnWriteList
import com.squareup.sqldelight.db.SqlCursor
import com.squareup.sqldelight.db.SqlDriver
import com.vickikbt.devtyme.`data`.cache.sqldelight.AppDatabase
import com.vickikbt.shared.`data`.cache.sqldelight.AppDatabaseQueries
import com.vickikbt.shared.`data`.cache.sqldelight.IsMovieFavourite
import com.vickikbt.shared.`data`.cache.sqldelight.MovieEntity
import kotlin.Any
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
          |adult TEXT DEFAULT NULL,
          |backdropPath TEXT DEFAULT NULL,
          |id INTEGER DEFAULT NULL,
          |originalLanguage TEXT DEFAULT NULL,
          |originalTitle TEXT DEFAULT NULL,
          |overview TEXT DEFAULT NULL,
          |popularity TEXT DEFAULT NULL,
          |posterPath TEXT DEFAULT NULL,
          |releaseDate TEXT DEFAULT NULL,
          |title TEXT DEFAULT NULL,
          |video TEXT DEFAULT NULL,
          |voteAverage TEXT DEFAULT NULL,
          |voteCount INTEGER DEFAULT NULL,
          |category TEXT DEFAULT NULL,
          |isFavourite TEXT DEFAULT NULL,
          |cacheId INTEGER DEFAULT 0 PRIMARY KEY
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE MovieDetailsEntity(
          |adult TEXT DEFAULT NULL,
          |backdropPath TEXT DEFAULT NULL,
          |homePage TEXT DEFAULT NULL,
          |id INTEGER NOT NULL PRIMARY KEY,
          |imdbId TEXT DEFAULT NULL,
          |originalLanguage TEXT DEFAULT NULL,
          |originalTitle TEXT DEFAULT NULL,
          |overview TEXT DEFAULT NULL,
          |popularity TEXT DEFAULT NULL,
          |posterPath TEXT DEFAULT NULL,
          |releaseDate TEXT DEFAULT NULL,
          |runtime INTEGER DEFAULT NULL,
          |status TEXT DEFAULT NULL,
          |tagline TEXT DEFAULT NULL,
          |title TEXT DEFAULT NULL,
          |video TEXT DEFAULT NULL,
          |voteAverage TEXT DEFAULT NULL,
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

  internal val getmMovies: MutableList<Query<*>> = copyOnWriteList()

  internal val isCategoryCacheAvailable: MutableList<Query<*>> = copyOnWriteList()

  internal val getFavouriteMovies: MutableList<Query<*>> = copyOnWriteList()

  internal val isMovieFavourite: MutableList<Query<*>> = copyOnWriteList()

  public override fun <T : Any> getNowPlayingMovies(category: String?, mapper: (
    adult: String?,
    backdropPath: String?,
    id: Long?,
    originalLanguage: String?,
    originalTitle: String?,
    overview: String?,
    popularity: String?,
    posterPath: String?,
    releaseDate: String?,
    title: String?,
    video: String?,
    voteAverage: String?,
    voteCount: Long?,
    category: String?,
    isFavourite: String?,
    cacheId: Long
  ) -> T): Query<T> = GetNowPlayingMoviesQuery(category) { cursor ->
    mapper(
      cursor.getString(0),
      cursor.getString(1),
      cursor.getLong(2),
      cursor.getString(3),
      cursor.getString(4),
      cursor.getString(5),
      cursor.getString(6),
      cursor.getString(7),
      cursor.getString(8),
      cursor.getString(9),
      cursor.getString(10),
      cursor.getString(11),
      cursor.getLong(12),
      cursor.getString(13),
      cursor.getString(14),
      cursor.getLong(15)!!
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

  public override fun <T : Any> getmMovies(category: String?, mapper: (
    adult: String?,
    backdropPath: String?,
    id: Long?,
    originalLanguage: String?,
    originalTitle: String?,
    overview: String?,
    popularity: String?,
    posterPath: String?,
    releaseDate: String?,
    title: String?,
    video: String?,
    voteAverage: String?,
    voteCount: Long?,
    category: String?,
    isFavourite: String?,
    cacheId: Long
  ) -> T): Query<T> = GetmMoviesQuery(category) { cursor ->
    mapper(
      cursor.getString(0),
      cursor.getString(1),
      cursor.getLong(2),
      cursor.getString(3),
      cursor.getString(4),
      cursor.getString(5),
      cursor.getString(6),
      cursor.getString(7),
      cursor.getString(8),
      cursor.getString(9),
      cursor.getString(10),
      cursor.getString(11),
      cursor.getLong(12),
      cursor.getString(13),
      cursor.getString(14),
      cursor.getLong(15)!!
    )
  }

  public override fun getmMovies(category: String?): Query<MovieEntity> = getmMovies(category) {
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
    adult: String?,
    backdropPath: String?,
    id: Long?,
    originalLanguage: String?,
    originalTitle: String?,
    overview: String?,
    popularity: String?,
    posterPath: String?,
    releaseDate: String?,
    title: String?,
    video: String?,
    voteAverage: String?,
    voteCount: Long?,
    category: String?,
    isFavourite: String?,
    cacheId: Long
  ) -> T): Query<T> = Query(-2013212026, getFavouriteMovies, driver, "AppDatabase.sq",
      "getFavouriteMovies", "SELECT * FROM MovieEntity WHERE isFavourite=TRUE") { cursor ->
    mapper(
      cursor.getString(0),
      cursor.getString(1),
      cursor.getLong(2),
      cursor.getString(3),
      cursor.getString(4),
      cursor.getString(5),
      cursor.getString(6),
      cursor.getString(7),
      cursor.getString(8),
      cursor.getString(9),
      cursor.getString(10),
      cursor.getString(11),
      cursor.getLong(12),
      cursor.getString(13),
      cursor.getString(14),
      cursor.getLong(15)!!
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

  public override fun <T : Any> isMovieFavourite(id: Long?, mapper: (isFavourite: String?) -> T):
      Query<T> = IsMovieFavouriteQuery(id) { cursor ->
    mapper(
      cursor.getString(0)
    )
  }

  public override fun isMovieFavourite(id: Long?): Query<IsMovieFavourite> = isMovieFavourite(id) {
      isFavourite ->
    IsMovieFavourite(
      isFavourite
    )
  }

  public override fun saveMovies(MovieEntity: MovieEntity): Unit {
    driver.execute(46047888, """
    |INSERT OR REPLACE INTO MovieEntity(adult, backdropPath,id,originalLanguage,originalTitle,overview,popularity,posterPath,releaseDate,title,video,voteAverage,voteCount,category,isFavourite,cacheId)
    |VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """.trimMargin(), 16) {
      bindString(1, MovieEntity.adult)
      bindString(2, MovieEntity.backdropPath)
      bindLong(3, MovieEntity.id)
      bindString(4, MovieEntity.originalLanguage)
      bindString(5, MovieEntity.originalTitle)
      bindString(6, MovieEntity.overview)
      bindString(7, MovieEntity.popularity)
      bindString(8, MovieEntity.posterPath)
      bindString(9, MovieEntity.releaseDate)
      bindString(10, MovieEntity.title)
      bindString(11, MovieEntity.video)
      bindString(12, MovieEntity.voteAverage)
      bindLong(13, MovieEntity.voteCount)
      bindString(14, MovieEntity.category)
      bindString(15, MovieEntity.isFavourite)
      bindLong(16, MovieEntity.cacheId)
    }
    notifyQueries(46047888, {database.appDatabaseQueries.getFavouriteMovies +
        database.appDatabaseQueries.isMovieFavourite + database.appDatabaseQueries.getmMovies +
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
        database.appDatabaseQueries.isMovieFavourite + database.appDatabaseQueries.getmMovies +
        database.appDatabaseQueries.isCategoryCacheAvailable +
        database.appDatabaseQueries.getNowPlayingMovies})
  }

  public override fun updateIsMovieFavorite(cacheId: Long?): Unit {
    driver.execute(null,
        """INSERT OR REPLACE INTO MovieEntity(isFavourite) SELECT cacheId${ if (cacheId == null) " IS " else "=" }? WHERE changes()=0""",
        1) {
      bindLong(1, cacheId)
    }
    notifyQueries(-138599831, {database.appDatabaseQueries.getFavouriteMovies +
        database.appDatabaseQueries.isMovieFavourite + database.appDatabaseQueries.getmMovies +
        database.appDatabaseQueries.isCategoryCacheAvailable +
        database.appDatabaseQueries.getNowPlayingMovies})
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

  private inner class GetmMoviesQuery<out T : Any>(
    public val category: String?,
    mapper: (SqlCursor) -> T
  ) : Query<T>(getmMovies, mapper) {
    public override fun execute(): SqlCursor = driver.executeQuery(null,
        """SELECT * FROM MovieEntity WHERE category${ if (category == null) " IS " else "=" }?""",
        1) {
      bindString(1, category)
    }

    public override fun toString(): String = "AppDatabase.sq:getmMovies"
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
    public val id: Long?,
    mapper: (SqlCursor) -> T
  ) : Query<T>(isMovieFavourite, mapper) {
    public override fun execute(): SqlCursor = driver.executeQuery(null,
        """SELECT isFavourite FROM MovieEntity WHERE id${ if (id == null) " IS " else "=" }? AND isFavourite=TRUE""",
        1) {
      bindLong(1, id)
    }

    public override fun toString(): String = "AppDatabase.sq:isMovieFavourite"
  }
}
