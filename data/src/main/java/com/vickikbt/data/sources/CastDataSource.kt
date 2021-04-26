package com.vickikbt.data.sources

import androidx.lifecycle.MutableLiveData
import com.vickikbt.data.cache.AppDatabase
import com.vickikbt.data.mappers.toDomain
import com.vickikbt.data.mappers.toEntity
import com.vickikbt.data.models.entity.CastEntity
import com.vickikbt.data.network.ApiService
import com.vickikbt.data.util.Constants.API_KEY
import com.vickikbt.data.util.Coroutines
import com.vickikbt.data.util.SafeApiRequest
import com.vickikbt.domain.models.Cast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class CastDataSource @Inject constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) : SafeApiRequest() {

    private val _cast = MutableLiveData<CastEntity>()

    init {
        _cast.observeForever { cast ->
            Coroutines.io { saveMovieCast(cast) }
        }
    }

    //Save a movie cast to SQLite database
    private suspend fun saveMovieCast(castEntity: CastEntity) =
        appDatabase.castDao().saveMovieCast(castEntity)


    //Retrieves movie cast based on id from SQLite if not available makes a network call to retrieve movie casts from API
    suspend fun getMovieCast(movieId: Int): Flow<Cast>? {
        val movieCastCacheResponse = appDatabase.castDao().getMovieCast(movieId)

        return if (movieCastCacheResponse != null) {
            Timber.e("Fetching movie cast from SQLite database")
            flow { emit(movieCastCacheResponse.toDomain()) }
        } else {
            Timber.e("Fetching movie cast from SQLite database failed, fetching from network.")
            val movieCastNetworkResponse = safeApiRequest { apiService.fetchMovieCast(movieId, API_KEY, "en") }

            _cast.value = movieCastNetworkResponse.toEntity()

            flow { emit(movieCastNetworkResponse.toEntity().toDomain()) }
        }
    }


}