package com.vickikbt.shared.data.data_sources

import com.vickikbt.shared.data.mappers.toDomain
import com.vickikbt.shared.data.mappers.toEntity
import com.vickikbt.shared.data.network.ApiService
import com.vickikbt.shared.domain.models.Cast
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.domain.models.MovieDetails
import com.vickikbt.shared.domain.models.MovieVideo
import com.vickikbt.shared.domain.repositories.MovieDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MovieDetailsRepositoryImpl constructor(
    private val apiService: ApiService
) : MovieDetailsRepository {

    override suspend fun getMovieDetails(movieId: Int): Flow<MovieDetails?> {
        val networkResponse = apiService.fetchMovieDetails(movieId = movieId)

        return flowOf(networkResponse.toDomain())
    }

    override suspend fun getMovieCast(movieId: Int): Flow<Cast?> {
        val movieCastNetworkResponse = apiService.fetchMovieCast(movieId)

        return flowOf(movieCastNetworkResponse.toDomain())
    }

    override suspend fun getMovieVideos(movieId: Int): Flow<MovieVideo?> {
        val movieVideosNetworkResponse = apiService.fetchMovieVideos(movieId)

        return flowOf(movieVideosNetworkResponse.toDomain())
    }

    override suspend fun fetchSimilarMovies(movieId: Int): Flow<List<Movie>?> {
        val similarMoviesDto = apiService.fetchSimilarMovies(movieId).movies.map { it.toEntity() }
        return flowOf(similarMoviesDto.map { it.toDomain() })
    }

    override suspend fun isMovieFavorite(movieId: Int): Flow<Boolean?> {
        return flowOf(true)
    }
}
