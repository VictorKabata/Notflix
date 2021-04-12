package com.vickikbt.domain.repositories

import com.vickikbt.domain.models.Movie
import com.vickikbt.domain.models.MovieDetails
import kotlinx.coroutines.flow.Flow

interface IMovieDetailsRepository {

    //Get movie details from SQLite TODO:Query from local database
    //suspend fun getMovieDetails(movieId: Int): Flow<Movie>

    //Fetch movie details from network
    suspend fun fetchMovieDetails(movieId: Int): Flow<MovieDetails>

}