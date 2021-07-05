package com.vickikbt.repository.repositories.movie_details

import androidx.lifecycle.MutableLiveData
import com.vickikbt.cache.AppDatabase
import com.vickikbt.cache.models.CastEntity
import com.vickikbt.cache.models.MovieDetailsEntity
import com.vickikbt.cache.models.VideoEntity
import com.vickikbt.core.Constants.API_KEY
import com.vickikbt.network.ApiService
import com.vickikbt.repository.mappers.toDomain
import com.vickikbt.repository.mappers.toEntity
import com.vickikbt.repository.models.Cast
import com.vickikbt.repository.models.MovieDetails
import com.vickikbt.repository.models.SimilarResult
import com.vickikbt.repository.models.Video
import com.vickikbt.repository.utils.SafeApiRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) : SafeApiRequest(), MovieDetailsRepository {

    private val _movieDetails = MutableLiveData<MovieDetailsEntity>()
    private val _cast = MutableLiveData<CastEntity>()
    private val _videos = MutableLiveData<VideoEntity>()

    init {
        _movieDetails.observeForever { movieDetails ->
            com.vickikbt.core.Coroutines.io { saveMovieDetails(movieDetails) }
        }

        _cast.observeForever { cast ->
            com.vickikbt.core.Coroutines.io { saveMovieCast(cast) }
        }

        _videos.observeForever { videos ->
            com.vickikbt.core.Coroutines.io { saveMovieVideos(videos) }
        }
    }

    override suspend fun saveMovieDetails(movieDetailsEntity: MovieDetailsEntity) =
        appDatabase.movieDetailsDao().saveMovieDetails(movieDetailsEntity)


    override suspend fun getMovieDetails(movieId: Int): Flow<MovieDetails> {
        val movieDetailsCacheResponse = appDatabase.movieDetailsDao().getMovieDetails(movieId)

        return if (movieDetailsCacheResponse != null) {
            Timber.e("Fetching from SQLite database")
            flow { emit(movieDetailsCacheResponse.toDomain()) }
        } else {
            Timber.e("Fetching from SQLite database failed, fetching from network.")
            val movieDetailsNetworkResponse =
                safeApiRequest { apiService.fetchMovieDetails(movieId, API_KEY, "en") }

            _movieDetails.value = movieDetailsNetworkResponse.toEntity()

            flow { emit(movieDetailsNetworkResponse.toEntity().toDomain()) }
        }
    }

    override suspend fun saveMovieCast(castEntity: CastEntity) =
        appDatabase.castDao().saveMovieCast(castEntity)

    override suspend fun getMovieCast(movieId: Int): Flow<Cast> {
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

    override suspend fun saveMovieVideos(videoEntity: VideoEntity)= appDatabase.videsoDao().saveMovieVideo(videoEntity)

    override suspend fun getMovieVideos(movieId: Int): Flow<Video> {
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

    override suspend fun fetchSimilarMovies(movieId: Int): Flow<SimilarResult> {
        val similarMoviesDto = safeApiRequest { apiService.fetchSimilarMovies(movieId) }
        return flow { emit(similarMoviesDto.toEntity().toDomain()) }
    }


}