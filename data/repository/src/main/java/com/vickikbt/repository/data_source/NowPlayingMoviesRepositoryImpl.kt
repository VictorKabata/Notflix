package com.vickikbt.repository.data_source

import androidx.lifecycle.MutableLiveData
import com.vickikbt.cache.AppDatabase
import com.vickikbt.cache.datastore.TimeDatastore
import com.vickikbt.cache.models.MovieEntity
import com.vickikbt.domain.models.Movie
import com.vickikbt.domain.repository.NowPlayingMoviesRepository
import com.vickikbt.domain.utils.Constants
import com.vickikbt.domain.utils.Coroutines
import com.vickikbt.network.ApiService
import com.vickikbt.network.utils.SafeApiRequest
import com.vickikbt.repository.mappers.toDomain
import com.vickikbt.repository.mappers.toEntity
import com.vickikbt.repository.utils.TimeUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NowPlayingMoviesRepositoryImpl constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase,
    private val timeDatastore: TimeDatastore
) : NowPlayingMoviesRepository, SafeApiRequest() {

    private val moviesDao = appDatabase.moviesDao()
    private val nowPlayingMoviesLivedata = MutableLiveData<List<MovieEntity>>()

    init {
        nowPlayingMoviesLivedata.observeForever {
            Coroutines.io { saveNowPlayingMovies(it) }
        }
    }

    private suspend fun saveNowPlayingMovies(movies: List<MovieEntity>) =
        moviesDao.saveMovies(movieEntities = movies)

    override suspend fun fetchNowPlayingMovies(): Flow<List<Movie>> {
        val isCategoryCacheAvailable = moviesDao.isCategoryCacheAvailable("now_playing") > 0

        val lastSyncTime = timeDatastore.getSyncTime()
        val isTimeSurpassed = TimeUtil.isTimeWithinInterval(
            Constants.TimeInterval,
            lastSyncTime,
            System.currentTimeMillis()
        )

        return if (isCategoryCacheAvailable && !isTimeSurpassed) {
            val cacheResponse = moviesDao.getMovies("now_playing")
            cacheResponse.map { it.map { it.toDomain() } }
        } else {
            moviesDao.deleteMovies("now_playing")

            val networkResponse =
                safeApiRequest { apiService.fetchNowPlayingMovies() }.movies?.map {
                    it.toEntity(category = "now_playing")
                }
            nowPlayingMoviesLivedata.value = networkResponse!!

            val cacheResponse = moviesDao.getMovies("now_playing")

            timeDatastore.saveSyncTime(System.currentTimeMillis())

            cacheResponse.map { it.map { it.toDomain() } }
        }
    }

}