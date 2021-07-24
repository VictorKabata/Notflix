package com.vickikbt.domain.repository

import com.vickikbt.domain.models.UpcomingResult
import kotlinx.coroutines.flow.Flow

interface UpcomingMoviesRepository {

    suspend fun fetchUpcomingMovies(): Flow<UpcomingResult>

    //suspend fun saveUpcomingMovies(upcomingResult: UpcomingResult)

    //suspend fun savePopularMoviesAndShows()

    //suspend fun getPopularMoviesAndShows()

}