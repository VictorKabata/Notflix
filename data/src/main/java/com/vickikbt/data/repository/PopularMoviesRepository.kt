package com.vickikbt.data.repository

import com.vickikbt.data.sources.PopularMoviesDataSource
import com.vickikbt.domain.repositories.IPopularRepository
import javax.inject.Inject

class PopularMoviesRepository @Inject constructor(private val popularMoviesDataSource: PopularMoviesDataSource):IPopularRepository {

    override suspend fun fetchPopularMovies()=popularMoviesDataSource.fetchPopularMovies()

}