package com.vickikbt.domain.repository

import com.vickikbt.domain.models.Movie
import com.vickikbt.domain.models.UpcomingMovies
import kotlinx.coroutines.flow.Flow

interface UpcomingMoviesRepository {

    suspend fun fetchUpcomingMovies(): Flow<List<Movie>>

}