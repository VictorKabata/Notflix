package com.vickikbt.data.sources

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.vickikbt.data.cache.AppDatabase
import com.vickikbt.data.mappers.toDomain
import com.vickikbt.data.mappers.toEntity
import com.vickikbt.data.models.dto.MovieDetailsDto
import com.vickikbt.data.models.entity.MovieDetailsEntity
import com.vickikbt.data.network.ApiService
import com.vickikbt.data.util.Constants.API_KEY
import com.vickikbt.data.util.Constants.TAG
import com.vickikbt.data.util.Coroutines
import com.vickikbt.data.util.SafeApiRequest
import com.vickikbt.domain.models.MovieDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.singleOrNull
import retrofit2.Response
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
    fun getMovieDetails(movieId: Int): Flow<MovieDetails>? {
        val movieDetailsCacheResponse = appDatabase.movieDetailsDao().getPopularShows(movieId)
        return movieDetailsCacheResponse?.map { it.toDomain() }
    }

    //Retrieves movie detail based on id from API
    suspend fun fetchMovieDetails(movieId: Int): Flow<MovieDetails> {
        val movieDetailsNetworkResponse: MovieDetailsDto = safeApiRequest { apiService.fetchMovieDetails(movieId, API_KEY, "en") }

        _movieDetails.value = movieDetailsNetworkResponse.toEntity()

        //saveMovieDetails(movieDetailsNetworkResponse.toEntity())

        return flow { emit(movieDetailsNetworkResponse.toEntity()) }.map { it.toDomain() }
    }

}