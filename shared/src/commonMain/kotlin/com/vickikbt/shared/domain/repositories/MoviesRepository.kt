package com.vickikbt.shared.domain.repositories

import com.vickikbt.shared.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    /** Get movies based on category from cache*/
    @Deprecated("Pending caching implementation")
    suspend fun getMovies(category: String): Flow<List<Movie>>

    /** Fetch movies based on category from network*/
    suspend fun fetchMovies(category: String): Flow<List<Movie>>
}
