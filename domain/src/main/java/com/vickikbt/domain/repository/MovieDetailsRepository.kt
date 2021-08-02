package com.vickikbt.domain.repository

import com.vickikbt.domain.models.*
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {

    /**
     * Save a movie details to SQLite database
     */
    //suspend fun saveMovieDetails(movieDetails: MovieDetails)

    /**
     * Retrieves movie detail based on id from SQLite
     * if not available makes a network call to retrieve movie details from API
     */
    suspend fun getMovieDetails(movieId: Int): Flow<MovieDetails>?

    /**
     * Save movie cast to SQLite database
     */
    //suspend fun saveMovieCast(cast: Cast)

    /**
     * Gets movie cast from network and later local database
     */
    suspend fun getMovieCast(movieId: Int): Flow<Cast>?

    /**
     * Save movie videos to SQLite database
     */
    //suspend fun saveMovieVideos(video: Video)

    /**
     * Gets movie videos from network and later local database
     */
    suspend fun getMovieVideos(movieId: Int): Flow<MovieVideo>?

    /**
     * Gets similar movies from network
     */
    suspend fun fetchSimilarMovies(movieId: Int): Flow<SimilarMovies>


}