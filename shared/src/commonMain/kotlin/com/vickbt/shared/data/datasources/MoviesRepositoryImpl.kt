package com.vickbt.shared.data.datasources

import com.vickbt.shared.data.mappers.toDomain
import com.vickbt.shared.data.network.models.MovieResultsDto
import com.vickbt.shared.data.network.utils.safeApiCall
import com.vickbt.shared.domain.models.Movie
import com.vickbt.shared.domain.repositories.MoviesRepository
import com.vickbt.shared.utils.ResultState
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MoviesRepositoryImpl constructor(
    private val httpClient: HttpClient,
) : MoviesRepository {
    override suspend fun fetchNowPlayingMovies(page: Int): Flow<ResultState<List<Movie>?>> {
        return flowOf(
            safeApiCall {
                val response =
                    httpClient.get(urlString = "movie/now_playing") {
                        parameter("page", page)
                    }.body<MovieResultsDto>()

                response.movies?.map { it.toDomain() }
            },
        )
    }

    override suspend fun fetchTrendingMovies(
        mediaType: String,
        timeWindow: String,
        page: Int,
    ): Flow<ResultState<List<Movie>?>> {
        return flowOf(
            safeApiCall {
                val response =
                    httpClient.get(urlString = "trending/$mediaType/$timeWindow") {
                        parameter("page", page)
                    }.body<MovieResultsDto>()

                response.movies?.map { it.toDomain() }
            },
        )
    }

    override suspend fun fetchPopularMovies(page: Int): Flow<ResultState<List<Movie>?>> {
        return flowOf(
            safeApiCall {
                val response =
                    httpClient.get(urlString = "movie/popular") {
                        parameter("page", page)
                    }.body<MovieResultsDto>()

                response.movies?.map { it.toDomain() }
            },
        )
    }

    override suspend fun fetchUpcomingMovies(page: Int): Flow<ResultState<List<Movie>?>> {
        return flowOf(
            safeApiCall {
                val response =
                    httpClient.get(urlString = "movie/upcoming") {
                        parameter("page", page)
                    }.body<MovieResultsDto>()

                response.movies?.map { it.toDomain() }
            },
        )
    }

    /*override suspend fun getMovies(category: String): Flow<List<Movie>> {
        val cachedResponse = moviesDao.getMoviesByCategory(category = category)
            .map { it.map { movieEntity -> movieEntity.toDomain(category = category) } }
            .onEach { movies ->
                // if (movies.isEmpty()) fetchMovies(category = category)
            }

        return cachedResponse
    }*/

    override suspend fun searchMovie(
        movieName: String,
        page: Int,
    ): Flow<ResultState<List<Movie>?>> {
        return flowOf(
            safeApiCall {
                val response =
                    httpClient.get(urlString = "search/movie") {
                        parameter("query", movieName)
                        parameter("page", page)
                    }.body<MovieResultsDto>()

                response.movies?.map { it.toDomain() }
            },
        )
    }
}
