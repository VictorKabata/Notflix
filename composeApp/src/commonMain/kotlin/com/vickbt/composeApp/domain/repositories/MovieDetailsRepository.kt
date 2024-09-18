package com.vickbt.composeApp.domain.repositories

import com.vickbt.composeApp.domain.models.Cast
import com.vickbt.composeApp.domain.models.Movie
import com.vickbt.composeApp.domain.models.MovieDetails
import com.vickbt.composeApp.domain.utils.Constants.STARTING_PAGE_INDEX
import com.vickbt.composeApp.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {

    /**Fetch movie details from network source*/
    suspend fun fetchMovieDetails(movieId: Int): Flow<ResultState<MovieDetails>>

    /**Fetch movie cast from network source*/
    suspend fun fetchMovieCast(movieId: Int): Flow<ResultState<Cast>>

    /** Fetches similar movies from network source*/
    suspend fun fetchSimilarMovies(
        movieId: Int,
        page: Int = STARTING_PAGE_INDEX
    ): Flow<ResultState<List<Movie>?>>

    /**Save movie details to local cache*/
    suspend fun saveFavoriteMovie(movie: MovieDetails)

    /**Retrieve cached movie details from local cache based on its ID*/
    suspend fun getFavoriteMovie(movieId: Int): MovieDetails?

    /**Delete previously saved movie details from local cache*/
    suspend fun deleteFavoriteMovie(movieId: Int)

    /**Check if movie details record is available in the local cache*/
    suspend fun isMovieFavorite(movieId: Int): Flow<Boolean>
}
