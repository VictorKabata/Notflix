package com.vickikbt.data.sources

import androidx.lifecycle.MutableLiveData
import com.vickikbt.data.cache.AppDatabase
import com.vickikbt.data.mappers.toDomain
import com.vickikbt.data.mappers.toEntity
import com.vickikbt.data.models.entity.VideoEntity
import com.vickikbt.data.network.ApiService
import com.vickikbt.data.util.Constants.API_KEY
import com.vickikbt.data.util.Coroutines
import com.vickikbt.data.util.SafeApiRequest
import com.vickikbt.domain.models.Video
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class VideoDataSource @Inject constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) : SafeApiRequest() {

    private val _videos = MutableLiveData<VideoEntity>()

    init {
        _videos.observeForever { videos ->
            Coroutines.io { saveMovieVideos(videos) }
        }
    }

    //Save a movie videos to SQLite database
    private suspend fun saveMovieVideos(videoEntity: VideoEntity) =
        appDatabase.videsoDao().saveMovieVideo(videoEntity)


    //Retrieves movie videos based on id from SQLite if not available makes a network call to retrieve movie videos from API
    suspend fun getMovieCast(movieId: Int): Flow<Video>? {
        val movieVideosCacheResponse = appDatabase.videsoDao().getMovieVideo(movieId)

        return if (movieVideosCacheResponse != null) {
            Timber.e("Fetching movie videos from SQLite database")
            flow { emit(movieVideosCacheResponse.toDomain()) }
        } else {
            Timber.e("Fetching movie videos from SQLite database failed, fetching from network.")
            val movieVideosNetworkResponse = safeApiRequest { apiService.fetchMovieVideos(movieId, API_KEY, "en") }

            _videos.value = movieVideosNetworkResponse.toEntity()

            flow { emit(movieVideosNetworkResponse.toEntity().toDomain()) }
        }
    }


}