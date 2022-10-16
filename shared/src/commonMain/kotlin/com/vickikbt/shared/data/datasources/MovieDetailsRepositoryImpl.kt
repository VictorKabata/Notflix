package com.vickikbt.shared.data.datasources

import com.vickikbt.shared.data.cache.sqldelight.daos.MovieDao
import com.vickikbt.shared.data.mappers.toDomain
import com.vickikbt.shared.data.mappers.toEntity
import com.vickikbt.shared.data.network.ApiService
import com.vickikbt.shared.domain.models.Cast
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.domain.models.MovieDetails
import com.vickikbt.shared.domain.repositories.MovieDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class MovieDetailsRepositoryImpl constructor(
    private val apiService: ApiService,
    private val movieDao: MovieDao
) : MovieDetailsRepository {

    override suspend fun saveMovieDetails(movieDetail: MovieDetails) {
        movieDao.saveMovieDetails(movieDetailsEntity = movieDetail.toEntity())
    }

    override suspend fun getMovieDetails(id: Int): Flow<MovieDetails?> {
        val cacheResponse = movieDao.getMovieDetails(id = id).map { it?.toDomain() }

        return if (cacheResponse.first() != null) cacheResponse
        else fetchMovieDetails(movieId = id)
    }

    override suspend fun fetchMovieDetails(movieId: Int): Flow<MovieDetails> {
        val networkResponse = apiService.fetchMovieDetails(movieId = movieId)

        return flowOf(networkResponse.toDomain())
    }

    override suspend fun saveMovieCast(actor: Cast) {
        movieDao.saveMovieCast(cast = actor.toEntity())
    }

    override suspend fun getMovieCast(id: Int): Flow<Cast?> {
        val cacheResponse = movieDao.getMovieCast(id = id).map { it?.toDomain() }

        return if (cacheResponse.first() != null) cacheResponse
        else fetchMovieCast(movieId = id)
    }

    override suspend fun fetchMovieCast(movieId: Int): Flow<Cast?> {
        val movieCastNetworkResponse = apiService.fetchMovieCast(movieId)

        return flowOf(movieCastNetworkResponse.toDomain())
    }

    override suspend fun fetchSimilarMovies(movieId: Int): Flow<List<Movie>?> {
        val similarMoviesDto = apiService.fetchSimilarMovies(movieId).movies.map { it.toEntity() }
        return flowOf(similarMoviesDto.map { it.toDomain() })
    }

    override suspend fun isMovieFavorite(movieId: Int): Flow<Boolean?> {
        return flowOf(true)
    }
}
