package com.vickikbt.domain.repository

import com.vickikbt.domain.models.Movie
import com.vickikbt.domain.models.PopularMovies
import kotlinx.coroutines.flow.Flow

interface PopularMoviesRepository {

    suspend fun fetchPopularMovies(): Flow<List<Movie>>


}