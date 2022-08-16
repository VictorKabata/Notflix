package com.vickikbt.shared.domain.repositories

import com.vickikbt.shared.data.network.utils.NetworkResult
import com.vickikbt.shared.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    /**
     * Fetch movies based on category
     */
    suspend fun fetchMovies(category: String):  Flow<NetworkResult<List<Movie>>>
}
