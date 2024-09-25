package com.vickbt.composeApp.domain.repositories

import app.cash.paging.PagingData
import com.vickbt.composeApp.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    /** Fetch Now Playing movies from data source*/
    suspend fun fetchNowPlayingMovies(): Result<Flow<List<Movie>?>>

    /** Fetch Trending movies from data source*/
    suspend fun fetchTrendingMovies(
        mediaType: String = "movie",
        timeWindow: String = "week"
    ): Result<Flow<PagingData<Movie>>>

    /** Fetch Popular movies from data source*/
    suspend fun fetchPopularMovies(): Result<Flow<PagingData<Movie>>>

    /** Fetch Upcoming movies from data source*/
    suspend fun fetchUpcomingMovies(): Result<Flow<PagingData<Movie>>>
}
