package com.vickikbt.repository.repositories.movie_details

import com.vickikbt.cache.models.CastEntity
import com.vickikbt.cache.models.MovieDetailsEntity
import com.vickikbt.cache.models.VideoEntity
import com.vickikbt.repository.models.Cast
import com.vickikbt.repository.models.MovieDetails
import com.vickikbt.repository.models.SimilarResult
import com.vickikbt.repository.models.Video
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {

    /**
     * Save a movie details to SQLite database
     */
    suspend fun saveMovieDetails(movieDetailsEntity: MovieDetailsEntity)

    /**
     * Retrieves movie detail based on id from SQLite
     * if not available makes a network call to retrieve movie details from API
     */
    suspend fun getMovieDetails(movieId: Int): Flow<MovieDetails>?

    /**
     * Save movie cast to SQLite database
     */
    suspend fun saveMovieCast(castEntity: CastEntity)

    /**
     * Gets movie cast from network and later local database
     */
    suspend fun getMovieCast(movieId: Int): Flow<Cast>?

    /**
     * Save movie videos to SQLite database
     */
    suspend fun saveMovieVideos(videoEntity: VideoEntity)

    /**
     * Gets movie videos from network and later local database
     */
    suspend fun getMovieVideos(movieId: Int): Flow<Video>?

    /**
     * Gets similar movies from network
     */
    suspend fun fetchSimilarMovies(movieId: Int): Flow<SimilarResult>


}