package com.vickikbt.data.sources

import androidx.lifecycle.MutableLiveData
import com.vickikbt.data.cache.AppDatabase
import com.vickikbt.data.mappers.toDomain
import com.vickikbt.data.mappers.toEntity
import com.vickikbt.data.models.dto.MovieDetailsDto
import com.vickikbt.data.models.entity.MovieDetailsEntity
import com.vickikbt.data.network.ApiService
import com.vickikbt.data.util.Constants.API_KEY
import com.vickikbt.data.util.Coroutines
import com.vickikbt.data.util.SafeApiRequest
import com.vickikbt.domain.models.MovieDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class MovieDetailsDataSource @Inject constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) : SafeApiRequest() {

    private var movieDetailsDtoLiveData = MutableLiveData<MovieDetailsDto>()

    init {
        movieDetailsDtoLiveData.observeForever { movieDetailDto ->
            Coroutines.io { saveMovieDetails(movieDetailDto.toEntity()) }
        }
    }


    //Initialize fetching of movie details
    suspend fun getMovieDetails(movieId: Int): Flow<MovieDetails> {
        var movieDetail: MovieDetailsEntity? = null

        Timber.e("getMovieDetails: movieDetails outside: $movieDetail")

        appDatabase.movieDetailsDao().getMovieDetails(movieId).buffer()
            .collect { movieDetailsEntity ->
                //movieDetailsEntityLiveData.postValue(movieDetailsEntity)

                movieDetail = movieDetailsEntity

                Timber.e("getMovieDetails: movieDetails inside flow: $movieDetail")

                fetchMovieDetails(movieDetailsEntity, movieId)
            }

        Timber.e("getMovieDetails: movieDetails outside flow: $movieDetail")

        return flow { emit(movieDetail!!.toDomain()) }
    }

    //Check if movie details is in cache if not retrieve from network call
    private suspend fun fetchMovieDetails(
        movieDetailsEntity: MovieDetailsEntity?,
        movieId: Int
    ): Flow<MovieDetails> {
        return if (movieDetailsEntity == null) {
            val movieDetailsDto =
                safeApiRequest { apiService.fetchMovieDetails(movieId, API_KEY, "en") }

            Timber.e("Fetching movie details from API")

            movieDetailsDtoLiveData.postValue(movieDetailsDto)

            flow { emit(movieDetailsDto.toEntity().toDomain()) }

        } else {
            Timber.e("Fetching movie details from SQLite database")

            flow { emit(movieDetailsEntity.toDomain()) }
        }
    }

    //Save a movie details once it is opened
    private suspend fun saveMovieDetails(movieDetailsEntity: MovieDetailsEntity) =
        appDatabase.movieDetailsDao().saveMovieDetails(movieDetailsEntity)

}