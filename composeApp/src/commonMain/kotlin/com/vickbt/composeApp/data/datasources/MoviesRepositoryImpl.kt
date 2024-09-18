package com.vickbt.composeApp.data.datasources

import com.vickbt.composeApp.data.mappers.toDomain
import com.vickbt.composeApp.data.network.models.MovieResultsDto
import com.vickbt.composeApp.data.network.utils.safeApiCall
import com.vickbt.composeApp.domain.models.Movie
import com.vickbt.composeApp.domain.repositories.MoviesRepository
import com.vickbt.composeApp.utils.ResultState
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MoviesRepositoryImpl constructor(
    private val httpClient: HttpClient
) : MoviesRepository {

    override suspend fun fetchNowPlayingMovies(page: Int): Flow<ResultState<List<Movie>?>> {
        return flowOf(
            safeApiCall {
                val response = httpClient.get(urlString = "movie/now_playing") {
                    parameter("page", page)
                }.body<MovieResultsDto>()

                response.movies?.map { it.toDomain() }
            }
        )
    }

    override suspend fun fetchTrendingMovies(
        mediaType: String,
        timeWindow: String,
        page: Int
    ): Flow<ResultState<List<Movie>?>> {
        return flowOf(
            safeApiCall {
                val response = httpClient.get(urlString = "trending/$mediaType/$timeWindow") {
                    parameter("page", page)
                }.body<MovieResultsDto>()

                response.movies?.map { it.toDomain() }
            }
        )
    }

    override suspend fun fetchPopularMovies(page: Int): Flow<ResultState<List<Movie>?>> {
        return flowOf(
            safeApiCall {
                val response = httpClient.get(urlString = "movie/popular") {
                    parameter("page", page)
                }.body<MovieResultsDto>()

                response.movies?.map { it.toDomain() }
            }
        )
    }

    override suspend fun fetchUpcomingMovies(page: Int): Flow<ResultState<List<Movie>?>> {
        return flowOf(
            safeApiCall {
                val response = httpClient.get(urlString = "movie/upcoming") {
                    parameter("page", page)
                }.body<MovieResultsDto>()

                response.movies?.map { it.toDomain() }
            }
        )
    }
}
