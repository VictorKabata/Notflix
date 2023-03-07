package com.vickikbt.shared.data.network

import com.vickikbt.shared.data.network.models.CastDto
import com.vickikbt.shared.data.network.models.MovieDetailsDto
import com.vickikbt.shared.data.network.models.MovieResultsDto
import com.vickikbt.shared.data.network.models.MovieVideoDto
import com.vickikbt.shared.domain.utils.Constants.API_KEY
import com.vickikbt.shared.domain.utils.Constants.STARTING_PAGE_INDEX
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class ApiService constructor(private val httpClient: HttpClient) {

    suspend fun fetchNowPlayingMovies(page: Int = STARTING_PAGE_INDEX): MovieResultsDto {
        return httpClient.get(urlString = "https://api.themoviedb.org/3/movie/now_playing?api_key=$API_KEY") {
            parameter("page", page)
        }.body<MovieResultsDto>()
    }

    suspend fun fetchTrendingMovies(
        mediaType: String = "movie",
        timeWindow: String = "week",
        page: Int = STARTING_PAGE_INDEX
    ): MovieResultsDto {
        return httpClient.get(urlString = "https://api.themoviedb.org/3/trending/$mediaType/$timeWindow?api_key=$API_KEY") {
            parameter("page", page)
        }.body<MovieResultsDto>()
    }

    suspend fun fetchPopularMovies(page: Int = STARTING_PAGE_INDEX): MovieResultsDto {
        return httpClient.get(urlString = "https://api.themoviedb.org/3/movie/popular?api_key=$API_KEY") {
            parameter("page", page)
        }.body<MovieResultsDto>()
    }

    suspend fun fetchUpcomingMovies(page: Int = STARTING_PAGE_INDEX): MovieResultsDto {
        return httpClient.get(urlString = "https://api.themoviedb.org/3/movie/upcoming?api_key=$API_KEY") {
            parameter("page", page)
        }.body<MovieResultsDto>()
    }

    suspend fun fetchMovieDetails(movieId: Int): MovieDetailsDto {
        return httpClient.get(urlString = "https://api.themoviedb.org/3/movie/$movieId?api_key=$API_KEY") {
            parameter("movie_id", movieId)
        }.body<MovieDetailsDto>()
    }

    suspend fun fetchMovieCast(movieId: Int): CastDto {
        return httpClient.get(urlString = "https://api.themoviedb.org/3/movie/$movieId/credits?api_key=$API_KEY") {
            parameter("movie_id", movieId)
        }.body<CastDto>()
    }

    suspend fun fetchMovieVideos(movieId: Int): MovieVideoDto {
        return httpClient.get(urlString = "https://api.themoviedb.org/3/movie/$movieId/videos") {
            parameter("movie_id", movieId)
        }.body<MovieVideoDto>()
    }

    suspend fun fetchSimilarMovies(
        movieId: Int,
        page: Int = STARTING_PAGE_INDEX
    ): MovieResultsDto {
        return httpClient.get(urlString = "https://api.themoviedb.org/3/movie/$movieId/similar?api_key=$API_KEY") {
            parameter("page", page)
        }.body<MovieResultsDto>()
    }
}
