package com.vickbt.shared.data.datasources

import com.vickbt.shared.data.mappers.toDomain
import com.vickbt.shared.data.network.utils.safeApiCall
import com.vickbt.shared.domain.models.Movie
import com.vickbt.shared.domain.repositories.MoviesRepository
import com.vickbt.shared.domain.utils.Enums.MovieCategories
import com.vickbt.shared.utils.ResultState
import com.vickbt.shared.utils.capitalizeEachWord
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.victorkabata.models.CategoryDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MoviesRepositoryImpl(
    private val httpClient: HttpClient
) : MoviesRepository {

    override suspend fun fetchHomePage(): Flow<ResultState<Map<MovieCategories, List<Movie?>>>> {
        return flowOf(
            safeApiCall {
                val response = httpClient.get("/home").body<CategoryDto>()
                mapOf<MovieCategories, List<Movie?>>(
                    MovieCategories.valueOf(
                        response.name.replace(" ", "_").capitalizeEachWord()
                    ) to response.list.map { it.toDomain() })
            }
        )
    }

    /*override suspend fun searchMovie(
        movieName: String,
        page: Int
    ): Flow<ResultState<List<Movie>?>> {
        return flowOf(
            safeApiCall {
                val response = httpClient.get(urlString = "search/movie") {
                    parameter("query", movieName)
                    parameter("page", page)
                }.body<MovieResultsDto>()

                response.movies?.map { it.toDomain() }
            }
        )
    }*/
}
