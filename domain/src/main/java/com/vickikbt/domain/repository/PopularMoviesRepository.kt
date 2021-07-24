package com.vickikbt.domain.repository

import com.vickikbt.domain.models.PopularResult
import kotlinx.coroutines.flow.Flow

interface PopularMoviesRepository {

    suspend fun savePopularShows(popularResult: PopularResult)

    suspend fun fetchPopularMovies(): Flow<PopularResult>

    //suspend fun deletePopularShows()

    //suspend fun fetchPopularShows()

    //suspend fun savePopularMoviesAndShows()

    //suspend fun getPopularMoviesAndShows()

}