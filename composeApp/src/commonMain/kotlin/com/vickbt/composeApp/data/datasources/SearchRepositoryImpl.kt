package com.vickbt.composeApp.data.datasources

import com.vickbt.composeApp.data.mappers.toDomain
import com.vickbt.composeApp.data.network.models.MovieResultsDto
import com.vickbt.composeApp.data.network.utils.safeApiCall
import com.vickbt.composeApp.domain.models.Movie
import com.vickbt.composeApp.domain.repositories.SearchRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow

class SearchRepositoryImpl(
    private val httpClient: HttpClient
) : SearchRepository {

    override suspend fun searchMovie(
        movieName: String,
        page: Int
    ): Result<Flow<List<Movie>?>> {
        return safeApiCall {
            val response = httpClient.get(urlString = "search/movie") {
                parameter("query", movieName)
                parameter("page", page)
            }.body<MovieResultsDto>()

            response.movies?.map { it.toDomain() }
        }
    }
}
