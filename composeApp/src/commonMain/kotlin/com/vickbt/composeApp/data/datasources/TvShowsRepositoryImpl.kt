package com.vickbt.composeApp.data.datasources

import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import com.vickbt.composeApp.data.mappers.toDomain
import com.vickbt.composeApp.data.network.models.TvShowResultsDto
import com.vickbt.composeApp.data.network.utils.safeApiCall
import com.vickbt.composeApp.data.paging.BasePagingSource
import com.vickbt.composeApp.domain.models.TvShow
import com.vickbt.composeApp.domain.repositories.TvShowsRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow

class TvShowsRepositoryImpl(private val httpClient: HttpClient) : TvShowsRepository {

    private val pagingConfig = PagingConfig(pageSize = 20, enablePlaceholders = false)

    override suspend fun fetchAiringTodayTvShows(
        language: String,
        sortBy: String
    ): Result<Flow<List<TvShow>?>> {
        return safeApiCall {
            val response =
                httpClient.get(urlString = "discover/tv?include_adult=true&language=$language&page=1&sort_by=$sortBy&air_date.lte={max_date}&air_date.gte={min_date}").body<TvShowResultsDto>()

            response.tvShows?.map { it.toDomain() }
        }
    }

    override suspend fun fetchTrendingTVShows(
        timeWindow: String,
        language: String
    ): Result<Flow<PagingData<TvShow>>> {
        val pagingSource = BasePagingSource { page ->
            val response =
                httpClient.get(urlString = "trending/tv/$timeWindow?language=$language") {
                    parameter("page", page)
                }.body<TvShowResultsDto>().tvShows

            response?.map { it.toDomain() }
        }

        return runCatching {
            Pager(config = pagingConfig, pagingSourceFactory = { pagingSource }).flow
        }
    }

    override suspend fun fetchTopRatedTvShows(
        language: String,
        sortBy: String,
        voteCount: String
    ): Result<Flow<PagingData<TvShow>>> {
        val pagingSource = BasePagingSource { page ->
            val response =
                httpClient.get(urlString = "discover/tv?include_adult=true&language=$language&page=$page&sort_by=$sortBy&vote_count.gte=$voteCount")
                    .body<TvShowResultsDto>().tvShows

            response?.map { it.toDomain() }
        }

        return runCatching {
            Pager(config = pagingConfig, pagingSourceFactory = { pagingSource }).flow
        }
    }

    override suspend fun fetchPopularTvShows(
        language: String,
        sortBy: String
    ): Result<Flow<PagingData<TvShow>>> {
        val pagingSource = BasePagingSource { page ->
            val response =
                httpClient.get(urlString = "discover/tv?include_adult=true&language=$language&page=$page&sort_by=$sortBy")
                    .body<TvShowResultsDto>().tvShows

            response?.map { it.toDomain() }
        }

        return runCatching {
            Pager(config = pagingConfig, pagingSourceFactory = { pagingSource }).flow
        }
    }
}
