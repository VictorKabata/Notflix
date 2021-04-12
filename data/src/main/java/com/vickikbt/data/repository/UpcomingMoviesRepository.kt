package com.vickikbt.data.repository

import com.vickikbt.data.sources.UpcomingMoviesDataSource
import com.vickikbt.domain.repositories.IUpcomingRepository
import javax.inject.Inject

class UpcomingMoviesRepository @Inject constructor(private val upcomingMoviesDataSource: UpcomingMoviesDataSource) :
    IUpcomingRepository {

    override suspend fun fetchUpcomingMovies() = upcomingMoviesDataSource.fetchUpcomingMovies()

}