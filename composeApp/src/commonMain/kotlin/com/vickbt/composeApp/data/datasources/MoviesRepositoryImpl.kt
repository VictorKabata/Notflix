package com.vickbt.composeApp.data.datasources

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
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
        val pagingConfig = PagingConfig(pageSize = 5, enablePlaceholders = false)

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

    override suspend fun fetchPopularMovies(page: Int): Result<Flow<List<Movie>?>> {
        return safeApiCall {
            val response = httpClient.get(urlString = "movie/popular") {
                parameter("page", page)
            }.body<MovieResultsDto>()

            response.movies?.map { it.toDomain() }
        }
    }

    override suspend fun fetchUpcomingMovies(page: Int): Result<Flow<List<Movie>?>> {
        return safeApiCall {
            val response = httpClient.get(urlString = "movie/upcoming") {
                parameter("page", page)
            }.body<MovieResultsDto>()

            response.movies?.map { it.toDomain() }
        }
    }
}
