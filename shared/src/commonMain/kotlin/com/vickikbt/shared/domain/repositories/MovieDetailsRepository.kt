package com.vickikbt.shared.domain.repositories

import com.vickikbt.shared.domain.models.Cast
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.domain.models.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {

    /**Save favourite movie details*/
    suspend fun saveMovieDetails(movieDetail: MovieDetails)

    /**
     * Retrieves movie detail based on id from SQLite
     * if not available makes a network call to retrieve movie details from API
     */
    suspend fun getMovieDetails(id: Int): Flow<MovieDetails?>

    /**Fetch movie details from network source*/
    suspend fun fetchMovieDetails(movieId: Int): Flow<MovieDetails?>

    /**Cache favourite movie cast*/
    suspend fun saveMovieCast(actor: Cast)

    /**Get a list of cached favourite movie cast based on ID*/
    suspend fun getMovieCast(id: Int): Flow<Cast?>

    /**Gets movie cast from network and later local database*/
    suspend fun fetchMovieCast(movieId: Int): Flow<Cast?>

    /**
     * Gets similar movies from network
     */
    suspend fun fetchSimilarMovies(movieId: Int): Flow<List<Movie>?>

    suspend fun isMovieFavorite(movieId: Int): Flow<Boolean?>
}
