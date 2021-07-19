package com.vickikbt.repository.repositories.upcoming_movies

import com.vickikbt.cache.models.UpcomingResultEntity
import com.vickikbt.domain.models.UpcomingResult
import kotlinx.coroutines.flow.Flow

interface UpcomingRepository {

    suspend fun fetchUpcomingMovies():Flow<UpcomingResult>

    suspend fun saveUpcomingMovies(upcomingResultEntity: UpcomingResultEntity)

    //suspend fun savePopularMoviesAndShows()

    //suspend fun getPopularMoviesAndShows()

}