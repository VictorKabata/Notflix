package com.vickikbt.domain.repository

import com.vickikbt.domain.models.Movie
import com.vickikbt.domain.models.NowPlayingMovies
import com.vickikbt.domain.models.TrendingMovies
import kotlinx.coroutines.flow.Flow

interface TrendingMoviesRepository {

    suspend fun fetchTrendingMovies(): Flow<List<Movie>>

}