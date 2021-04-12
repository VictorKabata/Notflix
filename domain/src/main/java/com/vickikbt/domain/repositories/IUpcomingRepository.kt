package com.vickikbt.domain.repositories

import com.vickikbt.domain.models.PopularResult
import com.vickikbt.domain.models.UpcomingResult
import kotlinx.coroutines.flow.Flow

interface IUpcomingRepository {

    suspend fun fetchUpcomingMovies():Flow<UpcomingResult>

    //suspend fun fetchPopularShows()

    //suspend fun savePopularMoviesAndShows()

    //suspend fun getPopularMoviesAndShows()

}