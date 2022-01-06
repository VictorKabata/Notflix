package com.vickikbt.repository.repository.movies_repository

import androidx.lifecycle.MutableLiveData
import com.vickikbt.cache.AppDatabase
import com.vickikbt.cache.models.MovieEntity
import com.vickikbt.domain.models.Movie
import com.vickikbt.domain.utils.Coroutines
import com.vickikbt.network.ApiService
import com.vickikbt.network.utils.SafeApiRequest
import com.vickikbt.repository.mappers.toDomain
import com.vickikbt.repository.mappers.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MoviesRepositoryImpl constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) : MoviesRepository, SafeApiRequest() {
    private val moviesDao = appDatabase.moviesDao()

    private val _movieMutableLiveData = MutableLiveData<List<MovieEntity>>()

    init {
        _movieMutableLiveData.observeForever { movies ->
            Coroutines.io { moviesDao.saveMovies(movieEntities = movies) }
        }
    }

    private suspend fun saveMovies(movieEntities: List<MovieEntity>) =
        moviesDao.saveMovies(movieEntities)

    override suspend fun fetchMovies(category: String): Flow<List<Movie>> {
        val isCategoryCacheAvailable = moviesDao.isCategoryCacheAvailable(category) > 0

        //ToDo: Check sync time-IsTimeSurpassed

        return if (isCategoryCacheAvailable) {
            val cacheResponse = moviesDao.getMovies(category)
            cacheResponse.map { it.map { it.toDomain() } }
        } else {
            moviesDao.deleteMovies(category)

            val networkResponse =
                safeApiRequest { apiService.fetchNowPlayingMovies() }.movies?.map {
                    it.toEntity(category = category)
                }
            _movieMutableLiveData.value = networkResponse!!

            val cacheResponse = moviesDao.getMovies(category)

            //ToDo: Update sync time

            cacheResponse.map { it.map { it.toDomain() } }
        }
    }


}