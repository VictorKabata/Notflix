package com.vickbt.shared.domain.repositories

import com.vickbt.shared.domain.models.Cast
import com.vickbt.shared.domain.models.Movie
import com.vickbt.shared.domain.models.MovieDetails
import com.vickbt.shared.domain.utils.Constants.STARTING_PAGE_INDEX
import com.vickbt.shared.utils.NetworkResultState
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {

    /**Fetch movie details from network source*/
    suspend fun fetchMovieDetails(movieId: Int): Flow<NetworkResultState<MovieDetails>>

    /**Fetch movie cast from network source*/
    suspend fun fetchMovieCast(movieId: Int): Flow<NetworkResultState<Cast>>

    /** Fetches similar movies from network source*/
    suspend fun fetchSimilarMovies(
        movieId: Int,
        page: Int = STARTING_PAGE_INDEX
    ): Flow<NetworkResultState<List<Movie>?>>

    /**Save movie details to local cache*/
    suspend fun saveFavoriteMovie(movie: MovieDetails)

    /**Retrieve cached movie details from local cache based on its ID*/
    suspend fun getFavoriteMovie(movieId: Int): MovieDetails

    /**Delete previously saved movie details from local cache*/
    suspend fun deleteFavoriteMovie(movieId: Int)

    /**Check if movie details record is available in the local cache*/
    suspend fun isMovieFavorite(movieId: Int): Boolean?
}
