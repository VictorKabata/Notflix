package com.vickikbt.shared.data.network

import com.vickikbt.shared.data.network.models.*
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.request.*

class ApiServiceImpl constructor(private val httpClient: HttpClient) : ApiService {

    override suspend fun fetchNowPlayingMovies(page: Int, language: String): MovieResultsDto? {
        return try {
            httpClient.get<MovieResultsDto>(urlString = "movie/now_playing") {
                parameter("page", page)
                parameter("language", language)
            }
        } catch (e: Exception) {
            Napier.e("Error: ${e.message}")
            null
        }
    }

    override suspend fun fetchPopularMovies(page: Int, language: String): MovieResultsDto? {
        return try {
            httpClient.get<MovieResultsDto>(urlString = "movie/popular") {
                parameter("page", page)
                parameter("language", language)
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

    override suspend fun fetchUpcomingMovies(page: Int, language: String): MovieResultsDto? {
        return try {
            httpClient.get<MovieResultsDto>(urlString = "movie/upcoming") {
                parameter("page", page)
                parameter("language", language)
            }
        } catch (e: Exception) {
            Napier.e("Error: ${e.message}")
            null
        }
    }

    override suspend fun fetchMovieDetails(movieId: Int, language: String): MovieDetailsDto? {
        return try {
            httpClient.get<MovieDetailsDto>(urlString = "movie/$movieId") {
                parameter("movie_id", movieId)
                parameter("language", language)
            }
        } catch (e: Exception) {
            Napier.e("Error: ${e.message}")
            null
        }
    }

    override suspend fun fetchMovieCast(movieId: Int, language: String): CastDto? {
        return try {
            httpClient.get<CastDto>(urlString = "movie/$movieId/credits") {
                parameter("movie_id", movieId)
                parameter("language", language)
            }
        } catch (e: Exception) {
            Napier.e("Error: ${e.message}")
            null
        }
    }

    override suspend fun fetchMovieVideos(movieId: Int, language: String): MovieVideoDto? {
        return try {
            httpClient.get<MovieVideoDto>(urlString = "movie/$movieId/videos") {
                parameter("movie_id", movieId)
                parameter("language", language)
            }
        } catch (e: Exception) {
            Napier.e("Error: ${e.message}")
            null
        }
    }

    override suspend fun fetchSimilarMovies(
        movieId: Int,
        page: Int,
        language: String
    ): SimilarMoviesDto? {
        return try {
            httpClient.get<SimilarMoviesDto>(urlString = "movie/$movieId/similar") {
                parameter("page", page)
                parameter("language", language)
            }
        } catch (e: Exception) {
            Napier.e("Error: ${e.message}")
            null
        }
    }
}
