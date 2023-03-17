package com.vickikbt.shared.data.network

import com.vickikbt.shared.data.network.models.CastDto
import com.vickikbt.shared.data.network.models.MovieDetailsDto
import com.vickikbt.shared.data.network.models.MovieResultsDto
import com.vickikbt.shared.data.network.models.MovieVideoDto
import com.vickikbt.shared.domain.utils.Constants.STARTING_PAGE_INDEX
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class ApiService constructor(private val httpClient: HttpClient) {

    suspend fun fetchMovieDetails(movieId: Int): MovieDetailsDto {
        return httpClient.get(urlString = "movie/$movieId").body<MovieDetailsDto>()
    }

    suspend fun fetchMovieCast(movieId: Int): CastDto {
        return httpClient.get(urlString = "movie/$movieId/credits").body<CastDto>()
    }

    suspend fun fetchMovieVideos(movieId: Int): MovieVideoDto {
        return httpClient.get(urlString = "movie/$movieId/videos") {
            parameter("movie_id", movieId)
        }.body<MovieVideoDto>()
    }

    suspend fun fetchSimilarMovies(
        movieId: Int,
        page: Int = STARTING_PAGE_INDEX
    ): MovieResultsDto {
        return httpClient.get(urlString = "movie/$movieId/similar") {
            parameter("page", page)
        }.body<MovieResultsDto>()
    }
}
