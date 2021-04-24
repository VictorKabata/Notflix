package com.vickikbt.data.repository

import com.vickikbt.data.sources.MovieDetailsDataSource
import com.vickikbt.domain.repositories.IMovieDetailsRepository

class MovieDetailsRepository constructor(private val movieDetailsDetailsDataSource: MovieDetailsDataSource) :
    IMovieDetailsRepository {


    override suspend fun fetchMovieDetails(movieId: Int) = movieDetailsDetailsDataSource.getMovieDetails(movieId)
}