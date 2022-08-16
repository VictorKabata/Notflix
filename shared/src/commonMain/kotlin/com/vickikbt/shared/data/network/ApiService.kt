package com.vickikbt.shared.data.network

import com.vickikbt.shared.data.network.models.CastDto
import com.vickikbt.shared.data.network.models.MovieDetailsDto
import com.vickikbt.shared.data.network.models.MovieResultsDto
import com.vickikbt.shared.data.network.models.MovieVideoDto
import com.vickikbt.shared.domain.utils.Constants.STARTING_PAGE_INDEX
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.request.*

class ApiService constructor(private val httpClient: HttpClient) {

    suspend fun fetchNowPlayingMovies(page: Int = STARTING_PAGE_INDEX): MovieResultsDto? {
        return try {
            httpClient.get<MovieResultsDto>(urlString = "movie/now_playing") {
                parameter("page", page)
            }
        } catch (e: Exception) {
            Napier.e("Error: ${e.message}")
            null
        }
    }

    suspend fun fetchPopularMovies(page: Int = STARTING_PAGE_INDEX): MovieResultsDto? {
        return try {
            httpClient.get<MovieResultsDto>(urlString = "movie/popular") {
                parameter("page", page)
            }
        } catch (e: Exception) {
            Napier.e("Error: ${e.message}")
            null
        }
    }

    suspend fun fetchTrendingMovies(
        mediaType: String = "movie",
        timeWindow: String = "week",
        page: Int = STARTING_PAGE_INDEX
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

    suspend fun fetchUpcomingMovies(page: Int = STARTING_PAGE_INDEX): MovieResultsDto? {
        return try {
            httpClient.get<MovieResultsDto>(urlString = "movie/upcoming") {
                parameter("page", page)
            }
        } catch (e: Exception) {
            Napier.e("Error: ${e.message}")
            null
        }
    }

    suspend fun fetchMovieDetails(movieId: Int): MovieDetailsDto? {
        return try {
            httpClient.get<MovieDetailsDto>(urlString = "movie/$movieId") {
                parameter("movie_id", movieId)
            }
        } catch (e: Exception) {
            Napier.e("Error: ${e.message}")
            null
        }
    }

    suspend fun fetchMovieCast(movieId: Int): CastDto? {
        return try {
            httpClient.get<CastDto>(urlString = "movie/$movieId/credits") {
                parameter("movie_id", movieId)
            }
        } catch (e: Exception) {
            Napier.e("Error: ${e.message}")
            null
        }
    }

    suspend fun fetchMovieVideos(movieId: Int): MovieVideoDto? {
        return try {
            httpClient.get<MovieVideoDto>(urlString = "movie/$movieId/videos") {
                parameter("movie_id", movieId)
            }
        } catch (e: Exception) {
            Napier.e("Error: ${e.message}")
            null
        }
    }

    suspend fun fetchSimilarMovies(
        movieId: Int,
        page: Int = STARTING_PAGE_INDEX
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
