package com.vickikbt.shared.domain.repositories

import com.vickikbt.shared.domain.models.Cast
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.domain.models.MovieDetails
import com.vickikbt.shared.domain.utils.Constants.STARTING_PAGE_INDEX
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {

    /**Save favourite movie details to local DB*/
    /*@Deprecated("Pending caching implementation")
    suspend fun saveMovieDetails(movieDetail: MovieDetails)*/

    /**
     * Retrieves movie detail based on id from local DB
     * if not available makes a network call to retrieve movie details from API
     */
    /*@Deprecated("Pending caching implementation")
    suspend fun getMovieDetails(movieId: Int): Flow<Result<MovieDetails>>*/

    /**Fetch movie details from network source*/
    suspend fun fetchMovieDetails(movieId: Int): Flow<Result<MovieDetails>>

    /**Fetch movie cast from network source*/
    suspend fun fetchMovieCast(movieId: Int): Flow<Result<Cast>>

    /** Fetches similar movies from network source*/
    suspend fun fetchSimilarMovies(
        movieId: Int,
        page: Int = STARTING_PAGE_INDEX
    ): Flow<Result<List<Movie>?>>
}
