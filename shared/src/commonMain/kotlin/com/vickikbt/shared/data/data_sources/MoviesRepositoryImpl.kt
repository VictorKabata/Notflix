package com.vickikbt.shared.data.data_sources

import com.vickikbt.shared.data.cache.sqldelight.MovieEntity
import com.vickikbt.shared.data.cache.sqldelight.daos.MoviesDao
import com.vickikbt.shared.data.mappers.toDomain
import com.vickikbt.shared.data.mappers.toEntity
import com.vickikbt.shared.data.network.ApiService
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.domain.repositories.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MoviesRepositoryImpl constructor(
    private val apiService: ApiService,
    private val moviesDao: MoviesDao,
) : MoviesRepository {

    private suspend fun saveMovies(movieEntities: List<MovieEntity>) {
        movieEntities.forEach {
            moviesDao.saveMovies(movieEntity = it)
        }
    }

    override suspend fun fetchNowPlayingMovies(category: String): Flow<List<Movie>?> {
        val isCategoryCacheAvailable =
            (moviesDao.isCategoryCacheAvailable(category)?.toInt() ?: 0) > 0
        val networkResponse = apiService.fetchNowPlayingMovies()?.movies

        return flowOf(networkResponse?.map { it.toEntity().toDomain() })
    }

    override suspend fun fetchMovies(category: String): Flow<List<Movie>?> {
        val isCategoryCacheAvailable = (moviesDao.isCategoryCacheAvailable(category) ?: 0) > 0
        val response = apiService.fetchUpcomingMovies()?.movies

        return flowOf(response?.map { it.toEntity().toDomain() })
    }
}
