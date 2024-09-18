package com.vickbt.shared.domain.repositories

import com.vickbt.shared.domain.models.Movie
import com.vickbt.shared.domain.utils.Constants.STARTING_PAGE_INDEX
import com.vickbt.shared.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    /** Fetch Now Playing movies from data source*/
    suspend fun fetchNowPlayingMovies(page: Int = STARTING_PAGE_INDEX): Flow<ResultState<List<Movie>?>>

    /** Fetch Trending movies from data source*/
    suspend fun fetchTrendingMovies(
        mediaType: String = "movie",
        timeWindow: String = "week",
        page: Int = STARTING_PAGE_INDEX
    ): Flow<ResultState<List<Movie>?>>

    /** Fetch Popular movies from data source*/
    suspend fun fetchPopularMovies(page: Int = STARTING_PAGE_INDEX): Flow<ResultState<List<Movie>?>>

    /** Fetch Upcoming movies from data source*/
    suspend fun fetchUpcomingMovies(page: Int = STARTING_PAGE_INDEX): Flow<ResultState<List<Movie>?>>
}
