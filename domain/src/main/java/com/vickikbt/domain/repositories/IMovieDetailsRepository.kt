package com.vickikbt.domain.repositories

import com.vickikbt.domain.models.Movie
import com.vickikbt.domain.models.MovieDetails
import kotlinx.coroutines.flow.Flow

interface IMovieDetailsRepository {

    //Fetch movie details from local database if available else fetch from network
    suspend fun fetchMovieDetails(movieId: Int): Flow<MovieDetails>

}