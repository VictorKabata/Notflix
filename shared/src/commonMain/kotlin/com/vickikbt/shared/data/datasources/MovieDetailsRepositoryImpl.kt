package com.vickikbt.shared.data.datasources

import com.vickikbt.shared.data.cache.sqldelight.daos.MovieDao
import com.vickikbt.shared.data.mappers.toDomain
import com.vickikbt.shared.data.mappers.toEntity
import com.vickikbt.shared.data.network.ApiService
import com.vickikbt.shared.data.network.utils.safeApiCall
import com.vickikbt.shared.domain.models.Cast
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.domain.models.MovieDetails
import com.vickikbt.shared.domain.repositories.MovieDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieDetailsRepositoryImpl constructor(
    private val apiService: ApiService,
    // private val movieDao: MovieDao
) : MovieDetailsRepository {

    /*override suspend fun saveMovieDetails(movieDetail: MovieDetails) {
        movieDao.saveMovieDetails(movieDetailsEntity = movieDetail.toEntity())
    }

    override suspend fun getMovieDetails(movieId: Int): Flow<Result<MovieDetails>> {
        val cacheResponse = movieDao.getMovieDetails(id = movieId).map { it?.toDomain() }

        // return if (cacheResponse.first() != null) cacheResponse
        return fetchMovieDetails(movieId = movieId)
    }*/

    override suspend fun fetchMovieDetails(movieId: Int): Flow<Result<MovieDetails>> = safeApiCall {
        val networkResponse = apiService.fetchMovieDetails(movieId = movieId)

        return@safeApiCall networkResponse.toDomain()
    }

    override suspend fun fetchMovieCast(movieId: Int): Flow<Result<Cast>> = safeApiCall {
        val movieCastNetworkResponse = apiService.fetchMovieCast(movieId)

        return@safeApiCall movieCastNetworkResponse.toDomain()
    }

    override suspend fun fetchSimilarMovies(movieId: Int): Flow<Result<List<Movie>?>> = safeApiCall {
        val similarMoviesDto = apiService.fetchSimilarMovies(movieId).movies?.map { it.toEntity() }
        return@safeApiCall similarMoviesDto?.map { it.toDomain() }
    }
}
