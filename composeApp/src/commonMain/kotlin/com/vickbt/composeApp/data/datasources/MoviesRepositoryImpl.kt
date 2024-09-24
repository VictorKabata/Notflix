package com.vickbt.composeApp.data.datasources

import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import com.vickbt.composeApp.data.mappers.toDomain
import com.vickbt.composeApp.data.network.models.MovieResultsDto
import com.vickbt.composeApp.data.network.utils.safeApiCall
import com.vickbt.composeApp.data.paging.BasePagingSource
import com.vickbt.composeApp.domain.models.Movie
import com.vickbt.composeApp.domain.repositories.MoviesRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow

class MoviesRepositoryImpl(
    private val httpClient: HttpClient
) : MoviesRepository {

    private val pagingConfig = PagingConfig(pageSize = 20, enablePlaceholders = false)

    override suspend fun fetchNowPlayingMovies(): Result<Flow<List<Movie>?>> {
        return safeApiCall {
            val response = httpClient.get(urlString = "movie/now_playing") {
                parameter("page", 1)
            }.body<MovieResultsDto>()

            response.movies?.map { it.toDomain() }
        }
    }

    override suspend fun fetchTrendingMovies(
        mediaType: String,
        timeWindow: String
    ): Result<Flow<PagingData<Movie>>> {
        val pagingSource = BasePagingSource { page ->
            val response = httpClient.get(urlString = "trending/$mediaType/$timeWindow") {
                parameter("page", page)
            }.body<MovieResultsDto>().movies

            response?.map { it.toDomain() }
        }

        return runCatching {
            Pager(
                config = pagingConfig,
                pagingSourceFactory = { pagingSource }
            ).flow
        }
    }

    override suspend fun fetchPopularMovies(): Result<Flow<PagingData<Movie>>> {
        val pagingSource = BasePagingSource { page ->
            val response = httpClient.get(urlString = "movie/popular") {
                parameter("page", page)
            }.body<MovieResultsDto>().movies

            response?.map { it.toDomain() }
        }

        return runCatching {
            Pager(
                config = pagingConfig,
                pagingSourceFactory = { pagingSource }
            ).flow
        }
    }

    override suspend fun fetchUpcomingMovies(): Result<Flow<PagingData<Movie>>> {
        val pagingSource = BasePagingSource { page ->
            val response = httpClient.get(urlString = "movie/upcoming") {
                parameter("page", page)
            }.body<MovieResultsDto>().movies

            response?.map { it.toDomain() }
        }

        return runCatching {
            Pager(
                config = pagingConfig,
                pagingSourceFactory = { pagingSource }
            ).flow
        }
    }
}
