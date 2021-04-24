package com.vickikbt.domain.repositories

import com.vickikbt.domain.models.MovieDetails
import kotlinx.coroutines.flow.Flow

interface IMovieDetailsRepository {

    //Fetch movie details from local database
    suspend fun getMovieDetails(movieId: Int): Flow<MovieDetails>?

    //Fetch movie details from api
    suspend fun fetchMovieDetails(movieId: Int): Flow<MovieDetails>

}