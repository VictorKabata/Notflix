package com.vickikbt.repository.repository.favorites_repository

import com.vickikbt.shared.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    suspend fun isMovieFavorite(movieId: Int): Flow<Boolean?>

    suspend fun updateIsMovieFavorite(cacheId: Int, isFavorite: Boolean)

    suspend fun getFavoriteMovies(): Flow<List<com.vickikbt.shared.domain.models.Movie>>
}
