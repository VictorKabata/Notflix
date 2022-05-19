package com.vickikbt.shared.domain.repositories

import com.vickikbt.shared.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun fetchMovies(category: String): Flow<List<Movie>?>
}
