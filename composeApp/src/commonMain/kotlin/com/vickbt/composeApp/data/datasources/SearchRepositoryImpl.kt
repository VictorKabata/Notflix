package com.vickbt.composeApp.data.datasources

import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import com.vickbt.composeApp.data.mappers.toDomain
import com.vickbt.composeApp.data.network.models.MovieResultsDto
import com.vickbt.composeApp.data.paging.BasePagingSource
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

    private val pagingConfig = PagingConfig(pageSize = 20, enablePlaceholders = false)

    override suspend fun searchMovie(movieName: String): Result<Flow<PagingData<Movie>>> {
        val pagingSource = BasePagingSource { page ->
            val response = httpClient.get(urlString = "search/movie") {
                parameter("query", movieName)
                parameter("page", page)
            }.body<MovieResultsDto>().movies

            response?.map { it.toDomain() }
        }

        return runCatching {
            Pager(
                config = pagingConfig,
                pagingSourceFactory = { pagingSource }
            ).flow
        }
    }
}
