package com.vickikbt.shared.domain.repositories

import com.vickikbt.shared.domain.models.MovieDetails
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    /**Check if movie is already favourite*/
    suspend fun isMovieFavorite(movieId: Int): Flow<Boolean?>

    /**Returns a list of movies that are favourite from the database*/
    suspend fun getFavouriteMovies(): Flow<List<MovieDetails>>

    /**Removes cached favourite movie from DB*/
    suspend fun deleteFavouriteMovie(movieId: Int)


}
