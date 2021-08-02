package com.vickikbt.domain.repository

import com.vickikbt.domain.models.Movie
import com.vickikbt.domain.models.NowPlayingMovies
import kotlinx.coroutines.flow.Flow

interface NowPlayingMoviesRepository {

    suspend fun fetchNowPlayingMovies(): Flow<List<Movie>>

}