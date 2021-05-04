package com.vickikbt.domain.repositories

import com.vickikbt.domain.models.Cast
import com.vickikbt.domain.models.MovieDetails
import com.vickikbt.domain.models.SimilarResult
import com.vickikbt.domain.models.Video
import kotlinx.coroutines.flow.Flow

interface IMovieDetailsRepository {

    //Gets movie details from network and later local database
    suspend fun getMovieDetails(movieId: Int): Flow<MovieDetails>?

    //Gets movie cast from network and later local database
    suspend fun getMovieCast(movieId: Int): Flow<Cast>?

    //Gets movie videos from network and later local database
    suspend fun getMovieVideos(movieId: Int): Flow<Video>?

    //Gets similar movies from network
    suspend fun getSimilarMovies(movieId: Int): Flow<SimilarResult>

}