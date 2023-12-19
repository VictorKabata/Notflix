package com.vickbt.shared.domain.repositories

import com.vickbt.shared.FavoriteMovieEntity
import com.vickbt.shared.domain.models.MovieDetails
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    /**Returns a list of movies that are favourite from the database*/
    suspend fun getFavouriteMovies(): Flow<List<FavoriteMovieEntity>>
}
