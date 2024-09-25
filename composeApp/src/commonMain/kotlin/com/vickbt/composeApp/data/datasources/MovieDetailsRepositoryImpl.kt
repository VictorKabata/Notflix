package com.vickbt.composeApp.data.datasources

import androidx.paging.PagingData
import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import com.vickbt.composeApp.data.cache.AppDatabase
import com.vickbt.composeApp.data.mappers.toDomain
import com.vickbt.composeApp.data.mappers.toEntity
import com.vickbt.composeApp.data.network.models.CastDto
import com.vickbt.composeApp.data.network.models.MovieDetailsDto
import com.vickbt.composeApp.data.network.models.MovieResultsDto
import com.vickbt.composeApp.data.network.utils.safeApiCall
import com.vickbt.composeApp.data.paging.BasePagingSource
import com.vickbt.composeApp.domain.models.Cast
import com.vickbt.composeApp.domain.models.Movie
import com.vickbt.composeApp.domain.models.MovieDetails
import com.vickbt.composeApp.domain.repositories.MovieDetailsRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class MovieDetailsRepositoryImpl(
    private val httpClient: HttpClient,
    private val appDatabase: AppDatabase
) : MovieDetailsRepository {

    private val pagingConfig = PagingConfig(pageSize = 20, enablePlaceholders = false)

    override suspend fun fetchMovieDetails(movieId: Int): Result<Flow<MovieDetails?>> {
        val isMovieCached = isMovieFavorite(movieId = movieId).getOrDefault(flowOf(false))
            .firstOrNull()

        return if (isMovieCached == true) {
            getFavoriteMovie(movieId = movieId)
        } else {
            safeApiCall {
                httpClient.get(urlString = "movie/$movieId").body<MovieDetailsDto>().toDomain()
            }
        }
    }

    override suspend fun fetchMovieCast(movieId: Int): Result<Flow<Cast>> {
        return safeApiCall {
            httpClient.get(urlString = "movie/$movieId/credits").body<CastDto>().toDomain()
        }
    }

    override suspend fun fetchSimilarMovies(movieId: Int): Result<Flow<PagingData<Movie>>> {
        val pagingSource = BasePagingSource { page ->
            val response = httpClient.get(urlString = "movie/$movieId/similar") {
                parameter("page", page)
            }.body<MovieResultsDto>()

            response.movies?.map { it.toDomain() }
        }

        return runCatching {
            Pager(
                config = pagingConfig,
                pagingSourceFactory = { pagingSource }
            ).flow
        }
    }

    override suspend fun saveFavoriteMovie(movie: MovieDetails) {
        runCatching { appDatabase.favoriteMovieDao().saveFavoriteMovie(movie = movie.toEntity()) }
    }

    override suspend fun getFavoriteMovie(movieId: Int): Result<Flow<MovieDetails?>> {
        return runCatching {
            appDatabase.favoriteMovieDao().getFavoriteMovie(id = movieId).map { it?.toDomain() }
        }
    }

    override suspend fun deleteFavoriteMovie(movieId: Int) {
        runCatching { appDatabase.favoriteMovieDao().deleteFavoriteMovie(id = movieId) }
    }

    override suspend fun isMovieFavorite(movieId: Int): Result<Flow<Boolean?>> {
        return runCatching { appDatabase.favoriteMovieDao().isMovieFavorite(id = movieId) }
    }
}
