package com.vickikbt.repository.repository.movies_repository

import androidx.paging.ExperimentalPagingApi
import com.vickikbt.shared.data.cache.sqldelight.MovieEntity
import com.vickikbt.shared.data.cache.sqldelight.daos.MoviesDao
import com.vickikbt.shared.data.mappers.toDomain
import com.vickikbt.shared.data.mappers.toEntity
import com.vickikbt.shared.data.network.ApiService
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.domain.repositories.MoviesRepository
import com.vickikbt.shared.domain.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

@ExperimentalPagingApi
class MoviesRepositoryImpl constructor(
    private val apiService: ApiService,
    private val moviesDao: MoviesDao
) : MoviesRepository {

    private suspend fun saveMovies(movieEntities: List<MovieEntity>) {
        movieEntities.forEach {
            moviesDao.saveMovies(movieEntity = it)
        }
    }

    override suspend fun fetchNowPlayingMovies(category: String): Flow<List<Movie>> {
        val isCategoryCacheAvailable =
            (moviesDao.isCategoryCacheAvailable(category)?.toInt() ?: 0) > 0
        val networkResponse = apiService.fetchNowPlayingMovies()?.movies

        return if (isCategoryCacheAvailable) {
            flowOf(networkResponse.toEnity())
        } else {

            val nowPlayingMoviesEntity = networkResponse?.map { it.toEntity(category = category) }
            _movieMutableLiveData.value = nowPlayingMoviesEntity!!

            moviesDao.getNowPlayingMovies()
                .map { movies -> movies.map { com.vickikbt.shared.data.mappers.toDomain() } }
        }
    }

    override suspend fun fetchMovies(category: String): Flow<List<MovieEntity>> {
        val isCategoryCacheAvailable = moviesDao.isCategoryCacheAvailable(category) > 0

        return flowOf(apiService.fetchUpcomingMovies()?.movies!!.map { it.toEntity(Constants.CATEGORY_UPCOMING_MOVIES) })
    }
}
