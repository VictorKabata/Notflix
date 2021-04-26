package com.vickikbt.domain.repositories

import com.vickikbt.domain.models.Cast
import com.vickikbt.domain.models.MovieDetails
import kotlinx.coroutines.flow.Flow

interface IMovieDetailsRepository {

    //Fetch movie details from local database
    suspend fun getMovieDetails(movieId: Int): Flow<MovieDetails>?

    suspend fun getMovieCast(movieId: Int):Flow<Cast>?

}