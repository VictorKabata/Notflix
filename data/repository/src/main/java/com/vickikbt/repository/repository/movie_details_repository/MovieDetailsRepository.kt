package com.vickikbt.repository.repository.movie_details_repository

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


}