package com.vickbt.composeApp.domain.repositories

import androidx.paging.PagingData
import com.vickbt.composeApp.domain.models.Cast
import com.vickbt.composeApp.domain.models.Movie
import com.vickbt.composeApp.domain.models.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {

    /**Fetch movie details from network source*/
    suspend fun fetchMovieDetails(movieId: Int): Result<Flow<MovieDetails?>>

    /**Fetch movie cast from network source*/
    suspend fun fetchMovieCast(movieId: Int): Result<Flow<Cast>>

    /** Fetches similar movies from network source*/
    suspend fun fetchSimilarMovies(movieId: Int): Result<Flow<PagingData<Movie>>>

    /**Save movie details to local cache*/
    suspend fun saveFavoriteMovie(movie: MovieDetails)

    /**Retrieve cached movie details from local cache based on its ID*/
    suspend fun getFavoriteMovie(movieId: Int): Result<Flow<MovieDetails?>>

    /**Delete previously saved movie details from local cache*/
    suspend fun deleteFavoriteMovie(movieId: Int)

    /**Check if movie details record is available in the local cache*/
    suspend fun isMovieFavorite(movieId: Int): Result<Flow<Boolean?>>
}
