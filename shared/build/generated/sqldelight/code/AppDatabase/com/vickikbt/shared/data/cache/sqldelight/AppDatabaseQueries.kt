package com.vickikbt.shared.`data`.cache.sqldelight

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.Transacter
import kotlin.Any
import kotlin.Boolean
import kotlin.Double
import kotlin.Long
import kotlin.String
import kotlin.Unit

public interface AppDatabaseQueries : Transacter {
  public fun <T : Any> getNowPlayingMovies(category: String?, mapper: (
    adult: Boolean?,
    backdropPath: String?,
    id: Long?,
    originalLanguage: String?,
    originalTitle: String?,
    overview: String?,
    popularity: Double?,
    posterPath: String?,
    releaseDate: String?,
    title: String?,
    video: Boolean?,
    voteAverage: Double?,
    voteCount: Long?,
    category: String?,
    isFavourite: Boolean?,
    cacheId: Long
  ) -> T): Query<T>

  public fun getNowPlayingMovies(category: String?): Query<MovieEntity>

  public fun <T : Any> getmMovies(category: String?, mapper: (
    adult: Boolean?,
    backdropPath: String?,
    id: Long?,
    originalLanguage: String?,
    originalTitle: String?,
    overview: String?,
    popularity: Double?,
    posterPath: String?,
    releaseDate: String?,
    title: String?,
    video: Boolean?,
    voteAverage: Double?,
    voteCount: Long?,
    category: String?,
    isFavourite: Boolean?,
    cacheId: Long
  ) -> T): Query<T>

  public fun getmMovies(category: String?): Query<MovieEntity>

  public fun isCategoryCacheAvailable(category: String?): Query<Long>

  public fun <T : Any> getFavouriteMovies(mapper: (
    adult: Boolean?,
    backdropPath: String?,
    id: Long?,
    originalLanguage: String?,
    originalTitle: String?,
    overview: String?,
    popularity: Double?,
    posterPath: String?,
    releaseDate: String?,
    title: String?,
    video: Boolean?,
    voteAverage: Double?,
    voteCount: Long?,
    category: String?,
    isFavourite: Boolean?,
    cacheId: Long
  ) -> T): Query<T>

  public fun getFavouriteMovies(): Query<MovieEntity>

  public fun <T : Any> isMovieFavourite(id: Long?, mapper: (isFavourite: Boolean?) -> T): Query<T>

  public fun isMovieFavourite(id: Long?): Query<IsMovieFavourite>

  public fun saveMovies(MovieEntity: MovieEntity): Unit

  public fun deleteMovies(category: String?): Unit

  public fun updateIsMovieFavorite(cacheId: Long?): Unit
}
