package com.vickikbt.repository.repositories.favorites

import com.vickikbt.repository.models.FavoriteMovie
import com.vickikbt.repository.models.MovieDetails
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    suspend fun saveFavoriteMovie(favoriteMovie: FavoriteMovie)

    suspend fun getFavoriteMovies(): Flow<List<FavoriteMovie>>

    //suspend fun saveFavoriteMovieDetail(favoriteMovieDetails: MovieDetails)

}