package com.vickikbt.repository.repository.movies_repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.ExperimentalPagingApi
import com.vickikbt.cache.AppDatabase
import com.vickikbt.cache.models.MovieEntity
import com.vickikbt.shared.data.mappers.toDomain
import com.vickikbt.shared.data.mappers.toEntity
import com.vickikbt.shared.data.network.ApiService
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.domain.repositories.MoviesRepository
import com.vickikbt.shared.domain.utils.Constants
import com.vickikbt.shared.domain.utils.Coroutines
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

@ExperimentalPagingApi
class MoviesRepositoryImpl constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) : MoviesRepository {
    private val moviesDao = appDatabase.moviesDao()

    private val _movieMutableLiveData = MutableLiveData<List<MovieEntity>>()

    init {
        _movieMutableLiveData.observeForever { movies ->
            Coroutines.default { saveMovies(movieEntities = movies) }
        }
    }

    private suspend fun saveMovies(movieEntities: List<MovieEntity>) =
        moviesDao.saveMovies(movieEntities)

    override suspend fun fetchNowPlayingMovies(category: String): Flow<List<Movie>> {
        val isCategoryCacheAvailable = moviesDao.isCategoryCacheAvailable(category) > 0

        return if (isCategoryCacheAvailable) {
            moviesDao.getNowPlayingMovies().map { movies -> movies.map { com.vickikbt.shared.data.mappers.toDomain() } }
        } else {
            val networkResponse = apiService.fetchNowPlayingMovies()?.movies
            val nowPlayingMoviesEntity = networkResponse?.map { it.toEntity(category = category) }
            _movieMutableLiveData.value = nowPlayingMoviesEntity!!

            moviesDao.getNowPlayingMovies().map { movies -> movies.map { com.vickikbt.shared.data.mappers.toDomain() } }
        }
    }

    override suspend fun fetchMovies(category: String): Flow<List<MovieEntity>> {
        val isCategoryCacheAvailable = moviesDao.isCategoryCacheAvailable(category) > 0

        return flowOf(apiService.fetchUpcomingMovies()?.movies!!.map { it.toEntity(Constants.CATEGORY_UPCOMING_MOVIES) })
    }
}
