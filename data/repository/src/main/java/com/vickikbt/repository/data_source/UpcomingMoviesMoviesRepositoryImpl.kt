package com.vickikbt.repository.data_source

import androidx.lifecycle.MutableLiveData
import com.vickikbt.cache.AppDatabase
import com.vickikbt.cache.datastore.TimeDatastore
import com.vickikbt.cache.models.UpcomingResultEntity
import com.vickikbt.repository.mappers.toDomain
import com.vickikbt.domain.models.UpcomingResult
import com.vickikbt.domain.repository.UpcomingMoviesRepository
import com.vickikbt.domain.utils.Constants
import com.vickikbt.domain.utils.Coroutines
import com.vickikbt.network.ApiService
import com.vickikbt.repository.mappers.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UpcomingMoviesMoviesRepositoryImpl constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase,
    private val timeDatastore: TimeDatastore
) : com.vickikbt.network.utils.SafeApiRequest(), UpcomingMoviesRepository {

    private val upcomingShowsLiveData = MutableLiveData<UpcomingResultEntity>()

    init {
        upcomingShowsLiveData.observeForever { result ->
            //Coroutines.io { saveUpcomingMovies(result) }
        }
    }

    override suspend fun fetchUpcomingMovies(): Flow<UpcomingResult> {
        val upcomingShowsDao = appDatabase.upcomingShowsDao()
        val isUpcomingShowsCacheAvailable = upcomingShowsDao.isUpcomingCacheAvailable() > 0

        val lastSyncTime = timeDatastore.getSyncTime()
        val isTimeSurpassed = com.vickikbt.repository.utils.TimeUtil.isTimeWithinInterval(
            Constants.TimeInterval,
            lastSyncTime,
            System.currentTimeMillis()
        )

        return if (isUpcomingShowsCacheAvailable && !isTimeSurpassed) {
            upcomingShowsDao.getUpcomingShows().map { it.toDomain() }
        } else {
            deletePopularShows()

            val moviesDto = safeApiRequest { apiService.fetchUpcomingMovies() }
            upcomingShowsLiveData.postValue(moviesDto.toEntity())

            timeDatastore.saveSyncTime(System.currentTimeMillis())

            upcomingShowsDao.getUpcomingShows().map { it.toDomain() }
        }
    }

    /*override suspend fun saveUpcomingMovies(upcomingResultEntity: UpcomingResultEntity) {
        Coroutines.io {
            appDatabase.upcomingShowsDao().saveUpcomingShows(upcomingResultEntity)
        }
    }*/

    private suspend fun deletePopularShows() {
        Coroutines.io { appDatabase.popularShowsDao().deletePopularShows() }
    }

}