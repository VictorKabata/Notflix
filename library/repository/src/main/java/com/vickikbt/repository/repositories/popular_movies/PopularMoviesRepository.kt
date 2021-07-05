package com.vickikbt.repository.repositories.popular_movies

import com.vickikbt.cache.models.PopularResultEntity
import com.vickikbt.domain.models.PopularResult
import kotlinx.coroutines.flow.Flow

interface PopularMoviesRepository {

    suspend fun savePopularShows(popularResultEntity: PopularResultEntity)

    suspend fun fetchPopularMovies():Flow<PopularResult>

    //suspend fun deletePopularShows()

    //suspend fun fetchPopularShows()

    //suspend fun savePopularMoviesAndShows()

    //suspend fun getPopularMoviesAndShows()

}