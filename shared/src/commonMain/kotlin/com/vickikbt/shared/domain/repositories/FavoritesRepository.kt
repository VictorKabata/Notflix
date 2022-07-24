package com.vickikbt.shared.domain.repositories

import com.vickikbt.shared.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    /**
     * Check if movie is already favourite
     */
    suspend fun isMovieFavorite(movieId: Int): Flow<Boolean?>

    // suspend fun updateIsMovieFavorite(cacheId: Int, isFavorite: Boolean)

    /**
     * Returns a list of movies that are favourite from the database
     */
    suspend fun getFavoriteMovies(): Flow<List<Movie>>
}
