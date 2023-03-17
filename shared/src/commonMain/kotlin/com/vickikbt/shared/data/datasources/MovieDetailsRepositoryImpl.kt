package com.vickikbt.shared.data.datasources

import com.vickikbt.shared.data.mappers.toDomain
import com.vickikbt.shared.data.network.models.CastDto
import com.vickikbt.shared.data.network.models.MovieDetailsDto
import com.vickikbt.shared.data.network.models.MovieResultsDto
import com.vickikbt.shared.data.network.utils.safeApiCall
import com.vickikbt.shared.domain.models.Cast
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.domain.models.MovieDetails
import com.vickikbt.shared.domain.repositories.MovieDetailsRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow

class MovieDetailsRepositoryImpl constructor(private val httpClient: HttpClient) : MovieDetailsRepository {

    override suspend fun fetchMovieDetails(movieId: Int): Flow<Result<MovieDetails>> {
        return safeApiCall {
            val response = httpClient.get(urlString = "movie/$movieId").body<MovieDetailsDto>()
            response.toDomain()
        }
    }

    override suspend fun fetchMovieCast(movieId: Int): Flow<Result<Cast>> {
        return safeApiCall {
            val response = httpClient.get(urlString = "movie/$movieId/credits").body<CastDto>()

            response.toDomain()
        }
    }

    override suspend fun fetchSimilarMovies(movieId: Int, page: Int): Flow<Result<List<Movie>?>> {
        return safeApiCall {
            val response = httpClient.get(urlString = "movie/$movieId/similar") {
                parameter("page", page)
            }.body<MovieResultsDto>()

            response.movies?.map { it.toDomain() }
        }
    }
}
