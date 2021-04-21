package com.vickikbt.data.repository

import com.vickikbt.data.sources.MovieDetailsDataSource
import com.vickikbt.domain.repositories.IMovieDetailsRepository
import javax.inject.Inject

class MovieDetailsRepository @Inject constructor(private val movieDetailsDataSource: MovieDetailsDataSource) :
    IMovieDetailsRepository {


    override suspend fun fetchMovieDetails(movieId: Int) =
        movieDetailsDataSource.getMovieDetails(movieId)
}