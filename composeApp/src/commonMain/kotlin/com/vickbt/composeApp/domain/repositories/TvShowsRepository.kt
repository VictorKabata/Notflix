package com.vickbt.composeApp.domain.repositories

import app.cash.paging.PagingData
import com.vickbt.composeApp.domain.models.TvShow
import kotlinx.coroutines.flow.Flow

interface TvShowsRepository {
    /** Fetch Tv Shows airing today from data source*/
    suspend fun fetchAiringTodayTvShows(
        language: String = "en-US",
        sortBy: String = "popularity.desc"
    ): Result<Flow<List<TvShow>?>>

    /** Fetch trending Tv Shows from data source*/
    suspend fun fetchTrendingTVShows(
        timeWindow: String = "week",
        language: String = "en-US"
    ): Result<Flow<PagingData<TvShow>>>

    /** Fetch top rated Tv Shows from data source*/
    suspend fun fetchTopRatedTvShows(
        language: String = "en-US",
        sortBy: String = "vote_average.desc",
        voteCount: String = "200"
    ): Result<Flow<PagingData<TvShow>>>

    /** Fetch popular Tv Shows from data source*/
    suspend fun fetchPopularTvShows(
        language: String = "en-US",
        sortBy: String = "popularity.desc"
    ): Result<Flow<PagingData<TvShow>>>
}
