package com.vickikbt.data.sources

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.vickikbt.data.cache.AppDatabase
import com.vickikbt.data.datastore.TimeDatastore
import com.vickikbt.data.mappers.toDomain
import com.vickikbt.data.mappers.toEntity
import com.vickikbt.data.models.entity.UpcomingResultEntity
import com.vickikbt.data.network.ApiService
import com.vickikbt.data.util.Constants
import com.vickikbt.data.util.Constants.API_KEY
import com.vickikbt.data.util.Coroutines
import com.vickikbt.data.util.SafeApiRequest
import com.vickikbt.data.util.TimeUtil
import com.vickikbt.domain.models.UpcomingResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UpcomingMoviesDataSource @Inject constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase,
    private val timeDatastore: TimeDatastore
) : SafeApiRequest() {

    private val upcomingShowsLiveData = MutableLiveData<UpcomingResultEntity>()

    init {
        upcomingShowsLiveData.observeForever { result ->
            saveUpcomingShows(result)
        }
    }

    //TODO: Move API_KEY TO APIKEY.PROPERTIES
    //TODO: Make Language Param dynamic based on device language
    suspend fun fetchUpcomingMovies(): Flow<UpcomingResult> {
        val upcomingShowsDao = appDatabase.upcomingShowsDao()
        val isUpcomingShowsCacheAvailable = upcomingShowsDao.isUpcomingCacheAvailable() > 0

        val lastSyncTime = timeDatastore.getSyncTime()
        val isTimeSurpassed = TimeUtil.isTimeWithinInterval(
            Constants.TimeInterval,
            lastSyncTime!!,
            System.currentTimeMillis()
        )

        return if (isUpcomingShowsCacheAvailable && !isTimeSurpassed) {
            Log.e("VickiKbt", "fetchUpcomingMovies: Data from cache")
            upcomingShowsDao.getUpcomingShows().map { it.toDomain() }
        } else {
            deletePopularShows()

            val moviesDto = safeApiRequest { apiService.fetchUpcomingMovies(API_KEY, "en") }
            upcomingShowsLiveData.postValue(moviesDto.toEntity())

            timeDatastore.saveSyncTime(System.currentTimeMillis())

            Log.e("VickiKbt", "fetchUpcomingMovies: Data from network")
            //flow { emit(moviesDto.toEntity()) }
            upcomingShowsDao.getUpcomingShows().map { it.toDomain() }
        }
    }

    private fun saveUpcomingShows(upcomingResultEntity: UpcomingResultEntity) {
        Coroutines.io { appDatabase.upcomingShowsDao().saveUpcomingShows(upcomingResultEntity) }
    }

    private suspend fun deletePopularShows() {
        Coroutines.io { appDatabase.popularShowsDao().deletePopularShows() }
    }

}