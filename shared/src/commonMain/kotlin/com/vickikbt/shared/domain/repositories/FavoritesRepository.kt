package com.vickikbt.shared.domain.repositories

import com.vickikbt.shared.domain.models.Cast
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.domain.models.MovieDetails
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    /**
     * Check if movie is already favourite
     */
    suspend fun isMovieFavorite(movieId: Int): Flow<Boolean?>

    suspend fun saveFavouriteMovie(movieDetail: MovieDetails)

    /**
     * Returns a list of movies that are favourite from the database
     */
    suspend fun getFavouriteMovies(): Flow<List<MovieDetails>>

    suspend fun saveFavouriteCast(actor: Cast)
}
