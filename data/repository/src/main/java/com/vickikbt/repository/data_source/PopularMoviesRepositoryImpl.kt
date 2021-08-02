package com.vickikbt.repository.data_source

import androidx.lifecycle.MutableLiveData
import com.vickikbt.cache.AppDatabase
import com.vickikbt.cache.datastore.TimeDatastore
import com.vickikbt.cache.models.MovieEntity
import com.vickikbt.domain.models.Movie
import com.vickikbt.domain.repository.PopularMoviesRepository
import com.vickikbt.domain.utils.Constants
import com.vickikbt.domain.utils.Coroutines
import com.vickikbt.network.ApiService
import com.vickikbt.repository.mappers.toDomain
import com.vickikbt.repository.mappers.toEntity
import com.vickikbt.repository.utils.TimeUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PopularMoviesRepositoryImpl constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase,
    private val timeDatastore: TimeDatastore
) : com.vickikbt.network.utils.SafeApiRequest(), PopularMoviesRepository {

    private val moviesDao = appDatabase.moviesDao()
    private val popularMoviesLivedata = MutableLiveData<List<MovieEntity>>()

    init {
        popularMoviesLivedata.observeForever {
            Coroutines.io { savePopularMovies(it) }
        }
    }

    private suspend fun savePopularMovies(movies: List<MovieEntity>) =
        moviesDao.saveMovies(movieEntities = movies)

    override suspend fun fetchPopularMovies(): Flow<List<Movie>> {
        val isCategoryCacheAvailable = moviesDao.isCategoryCacheAvailable("popular") > 0

        val lastSyncTime = timeDatastore.getSyncTime()
        val isTimeSurpassed = TimeUtil.isTimeWithinInterval(
            Constants.TimeInterval,
            lastSyncTime,
            System.currentTimeMillis()
        )

        return if (isCategoryCacheAvailable && !isTimeSurpassed) {
            val cacheResponse = moviesDao.getMovies("popular")
            cacheResponse.map { it.map { it.toDomain() } }
        } else {
            moviesDao.deleteMovies(category="popular")

            val networkResponse =
                safeApiRequest { apiService.fetchPopularMovies() }.movies?.map {
                    it.toEntity(category = "popular")
                }
            popularMoviesLivedata.value = networkResponse!!

            val cacheResponse = moviesDao.getMovies(category="popular")

            timeDatastore.saveSyncTime(System.currentTimeMillis())

            cacheResponse.map { it.map { it.toDomain() } }
        }
    }

}