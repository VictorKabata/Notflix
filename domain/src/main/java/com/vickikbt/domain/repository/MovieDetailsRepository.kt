package com.vickikbt.domain.repository

import com.vickikbt.domain.models.*
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {
    /**
     * Retrieves movie detail based on id from SQLite
     * if not available makes a network call to retrieve movie details from API
     */
    suspend fun getMovieDetails(movieId: Int): Flow<MovieDetails>

    /**
     * Gets movie cast from network and later local database
     */
    suspend fun getMovieCast(movieId: Int): Flow<Cast>

    /**
     * Gets movie videos from network and later local database
     */
    suspend fun getMovieVideos(movieId: Int): Flow<MovieVideo>

    /**
     * Gets similar movies from network
     */
    suspend fun fetchSimilarMovies(movieId: Int): Flow<SimilarMovies>

    /**
     * Save movie details to local storage
     */
    suspend fun saveMovieDetails(movieDetails: MovieDetails)

    /**
     * Save movie cast details to local storage
     */
    suspend fun saveMovieCast(cast: Cast)

    /**
     * Save movie videos to local storage
     */
    suspend fun saveMovieVideos(movieVideo: MovieVideo)

    suspend fun isMovieFavorite(movieId: Int): Flow<Boolean?>

    suspend fun updateMovieIsFavorite(cacheId: Int, isFavorite: Boolean)
}
