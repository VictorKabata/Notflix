package com.vickikbt.domain.repository

import com.vickikbt.domain.models.FavoriteMovie
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    suspend fun saveFavoriteMovie(favoriteMovie: FavoriteMovie)

    suspend fun getFavoriteMovies(): Flow<List<FavoriteMovie>>

    //suspend fun saveFavoriteMovieDetail(favoriteMovieDetails: MovieDetails)

}