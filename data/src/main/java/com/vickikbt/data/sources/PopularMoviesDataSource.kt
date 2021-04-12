package com.vickikbt.data.sources

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.vickikbt.data.cache.AppDatabase
import com.vickikbt.data.datastore.TimeDatastore
import com.vickikbt.data.mappers.toDomain
import com.vickikbt.data.mappers.toEntity
import com.vickikbt.data.models.entity.PopularResultEntity
import com.vickikbt.data.network.ApiService
import com.vickikbt.data.util.Constants
import com.vickikbt.data.util.Constants.API_KEY
import com.vickikbt.data.util.Coroutines
import com.vickikbt.data.util.SafeApiRequest
import com.vickikbt.data.util.TimeUtil
import com.vickikbt.domain.models.PopularResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PopularMoviesDataSource @Inject constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase,
    private val timeDatastore: TimeDatastore
) : SafeApiRequest() {

    private val popularShowsLiveData = MutableLiveData<PopularResultEntity>()

    init {
        popularShowsLiveData.observeForever { result ->
            savePopularShows(result)
        }
    }

    //TODO: Move API_KEY TO APIKEY.PROPERTIES
    //TODO: Make Language Param dynamic based on device language
    suspend fun fetchPopularMovies(): Flow<PopularResult> {
        val popularShowsDao = appDatabase.popularShowsDao()
        val isPopularShowsCacheAvailable = popularShowsDao.isPopularCacheAvailable() > 0

        val lastSyncTime = timeDatastore.getSyncTime()
        val isTimeSurpassed = TimeUtil.isTimeWithinInterval(
            Constants.TimeInterval,
            lastSyncTime,
            System.currentTimeMillis()
        )

        return if (isPopularShowsCacheAvailable && !isTimeSurpassed) {
            Log.e("VickiKbt", "fetchPopularMovies: Data from cache")
            popularShowsDao.getPopularShows().map { it.toDomain() }
        } else {

            deletePopularShows()

            val moviesDto = safeApiRequest { apiService.fetchPopularMovies(API_KEY, "en") }
            popularShowsLiveData.postValue(moviesDto.toEntity())

            timeDatastore.saveSyncTime(System.currentTimeMillis())

            Log.e("VickiKbt", "fetchPopularMovies: Data from network")
            //flow { emit(moviesDto.toDomain()) } From network
            return popularShowsDao.getPopularShows().map { it.toDomain() } //From cache database
        }
    }

    private fun savePopularShows(popularResultEntity: PopularResultEntity) {
        Coroutines.io { appDatabase.popularShowsDao().savePopularShows(popularResultEntity) }
    }

    private suspend fun deletePopularShows() = appDatabase.upcomingShowsDao().deleteUpcomingShows()

}