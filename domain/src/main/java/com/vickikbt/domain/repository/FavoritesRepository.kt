package com.vickikbt.domain.repository

import com.vickikbt.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    suspend fun saveFavoriteMovie(favoriteMovie: Movie)

    suspend fun getFavoriteMovies(): Flow<List<Movie>>

    //suspend fun saveFavoriteMovieDetail(favoriteMovieDetails: MovieDetails)

}