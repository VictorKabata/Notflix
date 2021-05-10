package com.vickikbt.domain.repositories

import com.vickikbt.domain.models.PopularResult
import kotlinx.coroutines.flow.Flow

interface IPopularRepository {

    suspend fun fetchPopularMovies():Flow<PopularResult>

    //suspend fun savePopularShows()

    //suspend fun deletePopularShows()

    //suspend fun fetchPopularShows()

    //suspend fun savePopularMoviesAndShows()

    //suspend fun getPopularMoviesAndShows()

}