package com.vickikbt.shared.data.network

import com.vickikbt.shared.data.network.models.CastDto
import com.vickikbt.shared.data.network.models.MovieDetailsDto
import com.vickikbt.shared.data.network.models.MovieResultsDto
import com.vickikbt.shared.data.network.models.MovieVideoDto
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.request.*

class ApiServiceImpl constructor(private val httpClient: HttpClient) : ApiService {

    override suspend fun fetchNowPlayingMovies(page: Int): MovieResultsDto? {
        return try {
            httpClient.get<MovieResultsDto>(urlString = "movie/now_playing") {
                parameter("page", page)
            }
        } catch (e: Exception) {
            Napier.e("Error: ${e.message}")
            null
        }
    }

    override suspend fun fetchPopularMovies(page: Int): MovieResultsDto? {
        return try {
            httpClient.get<MovieResultsDto>(urlString = "movie/popular") {
                parameter("page", page)
            }
        } catch (e: Exception) {
            Napier.e("Error: ${e.message}")
            null
        }
    }

    override suspend fun fetchTrendingMovies(
        mediaType: String,
        timeWindow: String,
        page: Int
    ): MovieResultsDto? {
        return try {
            httpClient.get<MovieResultsDto>(urlString = "trending/$mediaType/$timeWindow") {
                parameter("page", page)
            }
        } catch (e: Exception) {
            Napier.e("Error: ${e.message}")
            null
        }
    }

    override suspend fun fetchUpcomingMovies(page: Int): MovieResultsDto? {
        return try {
            httpClient.get<MovieResultsDto>(urlString = "movie/upcoming") {
                parameter("page", page)
            }
        } catch (e: Exception) {
            Napier.e("Error: ${e.message}")
            null
        }
    }

    override suspend fun fetchMovieDetails(movieId: Int): MovieDetailsDto? {
        return try {
            httpClient.get<MovieDetailsDto>(urlString = "movie/$movieId") {
                parameter("movie_id", movieId)
            }
        } catch (e: Exception) {
            Napier.e("Error: ${e.message}")
            null
        }
    }

    override suspend fun fetchMovieCast(movieId: Int): CastDto? {
        return try {
            httpClient.get<CastDto>(urlString = "movie/$movieId/credits") {
                parameter("movie_id", movieId)
            }
        } catch (e: Exception) {
            Napier.e("Error: ${e.message}")
            null
        }
    }

    override suspend fun fetchMovieVideos(movieId: Int): MovieVideoDto? {
        return try {
            httpClient.get<MovieVideoDto>(urlString = "movie/$movieId/videos") {
                parameter("movie_id", movieId)
            }
        } catch (e: Exception) {
            Napier.e("Error: ${e.message}")
            null
        }
    }

    override suspend fun fetchSimilarMovies(
        movieId: Int,
        page: Int
    ): MovieResultsDto? {
        return try {
            httpClient.get<MovieResultsDto>(urlString = "movie/$movieId/similar") {
                parameter("page", page)
            }
        } catch (e: Exception) {
            Napier.e("Error: ${e.message}")
            null
        }
    }
}
