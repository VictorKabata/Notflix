package com.vickbt.composeApp.domain.repositories

import com.vickbt.composeApp.domain.models.MovieDetails
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    /**Returns a list of movies that are favourite from the database*/
    suspend fun getFavouriteMovies(): Result<Flow<List<MovieDetails>?>>
}
