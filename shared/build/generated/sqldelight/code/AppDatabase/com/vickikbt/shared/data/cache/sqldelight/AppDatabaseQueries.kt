package com.vickikbt.shared.`data`.cache.sqldelight

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.Transacter
import kotlin.Any
import kotlin.Boolean
import kotlin.Double
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Unit

public interface AppDatabaseQueries : Transacter {
  public fun <T : Any> getNowPlayingMovies(category: String?, mapper: (
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
  ) -> T): Query<T>

  public fun getNowPlayingMovies(category: String?): Query<MovieEntity>

  public fun <T : Any> getMovies(category: String?, mapper: (
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
  ) -> T): Query<T>

  public fun getMovies(category: String?): Query<MovieEntity>

  public fun isCategoryCacheAvailable(category: String?): Query<Long>

  public fun <T : Any> getFavouriteMovies(mapper: (
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
  ) -> T): Query<T>

  public fun getFavouriteMovies(): Query<MovieEntity>

  public fun <T : Any> isMovieFavourite(id: Int?, mapper: (isFavourite: Boolean?) -> T): Query<T>

  public fun isMovieFavourite(id: Int?): Query<IsMovieFavourite>

  public fun <T : Any> getMovieDetails(id: Int, mapper: (
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
  ) -> T): Query<T>

  public fun getMovieDetails(id: Int): Query<MovieDetailsEntity>

  public fun isMovieDetailsAvailable(id: Int): Query<Long>

  public fun saveMovies(MovieEntity: MovieEntity): Unit

  public fun deleteMovies(category: String?): Unit

  public fun updateIsMovieFavorite(cacheId: Int?): Unit

  public fun saveMovieDetails(MovieDetailsEntity: MovieDetailsEntity): Unit

  public fun deleteAllMovieDetais(): Unit
}
