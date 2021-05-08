package com.vickikbt.data.sources

import androidx.lifecycle.MutableLiveData
import com.vickikbt.data.cache.AppDatabase
import com.vickikbt.data.mappers.toDomain
import com.vickikbt.data.mappers.toEntity
import com.vickikbt.data.models.entity.MovieDetailsEntity
import com.vickikbt.data.network.ApiService
import com.vickikbt.data.util.Constants.API_KEY
import com.vickikbt.data.util.Coroutines
import com.vickikbt.data.util.SafeApiRequest
import com.vickikbt.domain.models.MovieDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class MovieDetailsDataSource @Inject constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) : SafeApiRequest() {

    private val _movieDetails = MutableLiveData<MovieDetailsEntity>()

    init {
        _movieDetails.observeForever { movieDetails ->
            Coroutines.io { saveMovieDetails(movieDetails) }
        }
    }

    //Save a movie details to SQLite database
    private suspend fun saveMovieDetails(movieDetailsEntity: MovieDetailsEntity) =
        appDatabase.movieDetailsDao().saveMovieDetails(movieDetailsEntity)


    //Retrieves movie detail based on id from SQLite if not available makes a network call to retrieve movie details from API
    suspend fun getMovieDetails(movieId: Int): Flow<MovieDetails>? {
        val movieDetailsCacheResponse = appDatabase.movieDetailsDao().getMovieDetails(movieId)

        return if (movieDetailsCacheResponse != null) {
            Timber.e("Fetching from SQLite database")
            flow { emit(movieDetailsCacheResponse.toDomain()) }
        } else {
            Timber.e("Fetching from SQLite database failed, fetching from network.")
            val movieDetailsNetworkResponse = safeApiRequest { apiService.fetchMovieDetails(movieId, API_KEY, "en") }

            _movieDetails.value=movieDetailsNetworkResponse.toEntity()

            flow { emit(movieDetailsNetworkResponse.toEntity().toDomain()) }
        }
    }


}