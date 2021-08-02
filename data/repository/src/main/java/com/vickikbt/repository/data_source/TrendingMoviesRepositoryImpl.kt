package com.vickikbt.repository.data_source

import androidx.lifecycle.MutableLiveData
import com.vickikbt.cache.AppDatabase
import com.vickikbt.cache.datastore.TimeDatastore
import com.vickikbt.cache.models.MovieEntity
import com.vickikbt.domain.models.Movie
import com.vickikbt.domain.repository.TrendingMoviesRepository
import com.vickikbt.domain.utils.Constants
import com.vickikbt.domain.utils.Coroutines
import com.vickikbt.network.ApiService
import com.vickikbt.network.utils.SafeApiRequest
import com.vickikbt.repository.mappers.toDomain
import com.vickikbt.repository.mappers.toEntity
import com.vickikbt.repository.utils.TimeUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TrendingMoviesRepositoryImpl constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase,
    private val timeDatastore: TimeDatastore
) : SafeApiRequest(), TrendingMoviesRepository {

    private val moviesDao = appDatabase.moviesDao()
    private val trendingMoviesLivedata = MutableLiveData<List<MovieEntity>>()

    init {
        trendingMoviesLivedata.observeForever {
            Coroutines.io { saveTrendingMovies(it) }
        }
    }

    private suspend fun saveTrendingMovies(movies: List<MovieEntity>) =
        moviesDao.saveMovies(movieEntities = movies)

    override suspend fun fetchTrendingMovies(): Flow<List<Movie>> {
        val isCategoryCacheAvailable = moviesDao.isCategoryCacheAvailable("trending") > 0

        val lastSyncTime = timeDatastore.getSyncTime()
        val isTimeSurpassed = TimeUtil.isTimeWithinInterval(
            Constants.TimeInterval,
            lastSyncTime,
            System.currentTimeMillis()
        )

        return if (isCategoryCacheAvailable && !isTimeSurpassed) {
            val cacheResponse = moviesDao.getMovies("trending")
            cacheResponse.map { it.map { it.toDomain() } }
        } else {
            moviesDao.deleteMovies("trending")

            val networkResponse =
                safeApiRequest { apiService.fetchTrendingMovies() }.movies?.map {
                    it.toEntity(category = "trending")
                }
            trendingMoviesLivedata.value = networkResponse!!

            val cacheResponse = moviesDao.getMovies("trending")

            timeDatastore.saveSyncTime(System.currentTimeMillis())

            cacheResponse.map { it.map { it.toDomain() } }
        }
    }

}