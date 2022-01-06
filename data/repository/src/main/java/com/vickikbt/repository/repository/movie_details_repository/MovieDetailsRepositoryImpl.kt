package com.vickikbt.repository.repository.movie_details_repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.vickikbt.cache.AppDatabase
import com.vickikbt.cache.models.CastEntity
import com.vickikbt.cache.models.MovieDetailsEntity
import com.vickikbt.cache.models.MovieVideoEntity
import com.vickikbt.domain.models.Cast
import com.vickikbt.domain.models.MovieDetails
import com.vickikbt.domain.models.MovieVideo
import com.vickikbt.domain.models.SimilarMovies
import com.vickikbt.domain.utils.Coroutines
import com.vickikbt.network.ApiService
import com.vickikbt.network.utils.SafeApiRequest
import com.vickikbt.repository.mappers.toDomain
import com.vickikbt.repository.mappers.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MovieDetailsRepositoryImpl constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) : SafeApiRequest(), MovieDetailsRepository {

    private val movieDetailsDao = appDatabase.movieDetailsDao()

    private val _movieDetails = MutableLiveData<MovieDetailsEntity>()
    private val _cast = MutableLiveData<CastEntity>()
    private val _videos = MutableLiveData<MovieVideoEntity>()

    init {
        _movieDetails.observeForever { movieDetails ->
            Coroutines.io { saveMovieDetails(movieDetails) }
        }

        _cast.observeForever { cast ->
            Coroutines.io { saveMovieCast(cast) }
        }

        _videos.observeForever { videos ->
            Coroutines.io { saveMovieVideos(videos) }
        }
    }

    private suspend fun saveMovieDetails(movieDetailsEntity: MovieDetailsEntity) =
        appDatabase.movieDetailsDao().saveMovieDetails(movieDetailsEntity)

    override suspend fun getMovieDetails(movieId: Int): Flow<MovieDetails> {
        val isMovieDetailsCacheAvailable = movieDetailsDao.isMovieDetailsAvailable(movieId) > 0

        return if (isMovieDetailsCacheAvailable) {
            val movieDetailsCacheResponse = movieDetailsDao.getMovieDetails(movieId)
            movieDetailsCacheResponse.map { it.toDomain() }
        } else {
            val movieDetailsNetworkResponse =
                safeApiRequest { apiService.fetchMovieDetails(movieId) }

            _movieDetails.value = movieDetailsNetworkResponse.toEntity()

            val movieDetailsCacheResponse = movieDetailsDao.getMovieDetails(movieId)
            movieDetailsCacheResponse.map { it.toDomain() }
        }
    }

    private suspend fun saveMovieCast(castEntity: CastEntity) = appDatabase.castDao().saveMovieCast(castEntity)

    override suspend fun getMovieCast(movieId: Int): Flow<Cast> {
        val isMovieCacheAvailable=appDatabase.castDao().isMovieCastAvailable(movieId)>0

        return if (isMovieCacheAvailable) {
            val movieCastCacheResponse = appDatabase.castDao().getMovieCast(movieId)
            movieCastCacheResponse.map { it.toDomain() }
        } else {
            val movieCastNetworkResponse =
                safeApiRequest { apiService.fetchMovieCast(movieId) }

            Log.e("VickiKbt","Movie Cast Network Response: $movieCastNetworkResponse")

            _cast.value = movieCastNetworkResponse.toEntity()

            val movieCastCacheResponse = appDatabase.castDao().getMovieCast(movieId)
            movieCastCacheResponse.map { it.toDomain() }
        }
    }

    private suspend fun saveMovieVideos(movieVideoEntity: MovieVideoEntity) =
        appDatabase.videosDao().saveMovieVideo(movieVideoEntity)

    override suspend fun getMovieVideos(movieId: Int): Flow<MovieVideo> {
        val isMovieVideoCacheAvailable =
            appDatabase.videosDao().isMovieVideoCacheAvailable(movieId) > 0

        return if (isMovieVideoCacheAvailable) {
            val movieVideosCacheResponse = appDatabase.videosDao().getMovieVideo(movieId)
            movieVideosCacheResponse.map { it.toDomain() }
        } else {
            val movieVideosNetworkResponse =
                safeApiRequest { apiService.fetchMovieVideos(movieId) }

            _videos.value = movieVideosNetworkResponse.toEntity()

            flow { emit(movieVideosNetworkResponse.toEntity().toDomain()) }
        }
    }

    override suspend fun fetchSimilarMovies(movieId: Int): Flow<SimilarMovies> {
        val similarMoviesDto = safeApiRequest { apiService.fetchSimilarMovies(movieId) }
        return flow { emit(similarMoviesDto.toEntity().toDomain()) }
    }


}