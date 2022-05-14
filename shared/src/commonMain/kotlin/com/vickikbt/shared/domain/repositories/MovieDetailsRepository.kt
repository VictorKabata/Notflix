package com.vickikbt.shared.domain.repositories

import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {
    /**
     * Retrieves movie detail based on id from SQLite
     * if not available makes a network call to retrieve movie details from API
     */
    suspend fun getMovieDetails(movieId: Int): Flow<com.vickikbt.shared.domain.models.MovieDetails>

    /**
     * Gets movie cast from network and later local database
     */
    suspend fun getMovieCast(movieId: Int): Flow<com.vickikbt.shared.domain.models.Cast>

    /**
     * Gets movie videos from network and later local database
     */
    suspend fun getMovieVideos(movieId: Int): Flow<com.vickikbt.shared.domain.models.MovieVideo>

    /**
     * Gets similar movies from network
     */
    suspend fun fetchSimilarMovies(movieId: Int): Flow<com.vickikbt.shared.domain.models.SimilarMovies>

    /**
     * Save movie details to local storage
     */
    suspend fun saveMovieDetails(movieDetails: com.vickikbt.shared.domain.models.MovieDetails)

    /**
     * Save movie cast details to local storage
     */
    suspend fun saveMovieCast(cast: com.vickikbt.shared.domain.models.Cast)

    /**
     * Save movie videos to local storage
     */
    suspend fun saveMovieVideos(movieVideo: com.vickikbt.shared.domain.models.MovieVideo)

    suspend fun isMovieFavorite(movieId: Int): Flow<Boolean?>

    suspend fun updateMovieIsFavorite(cacheId: Int, isFavorite: Boolean)
}
