package com.vickikbt.data.repository

import com.vickikbt.data.sources.MovieDetailsDataSource
import com.vickikbt.domain.models.MovieDetails
import com.vickikbt.domain.repositories.IMovieDetailsRepository
import kotlinx.coroutines.flow.Flow

class MovieDetailsRepository constructor(private val movieDetailsDetailsDataSource: MovieDetailsDataSource) :
    IMovieDetailsRepository {

    override suspend fun getMovieDetails(movieId: Int)=movieDetailsDetailsDataSource.getMovieDetails(movieId)
}