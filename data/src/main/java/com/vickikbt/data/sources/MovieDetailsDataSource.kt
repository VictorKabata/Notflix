package com.vickikbt.data.sources

import androidx.lifecycle.MutableLiveData
import com.vickikbt.data.cache.AppDatabase
import com.vickikbt.data.models.entity.MovieDetailsEntity
import com.vickikbt.data.network.ApiService
import com.vickikbt.data.util.Coroutines
import javax.inject.Inject

class MovieDetailsDataSource @Inject constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) {

    private val _movieDetails=MutableLiveData<MovieDetailsEntity>()

    init {
        _movieDetails.observeForever { movieDetails->
            Coroutines.io {  }
        }
    }

    /*private suspend fun saveMovieDetails(movieDetailsEntity: MovieDetailsEntity) =
        appDatabase.movieDetailsDao().saveMovieDetails(movieDetailsEntity)*/

}