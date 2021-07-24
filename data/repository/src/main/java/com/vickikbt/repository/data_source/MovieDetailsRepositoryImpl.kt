package com.vickikbt.repository.data_source

import androidx.lifecycle.MutableLiveData
import com.vickikbt.cache.AppDatabase
import com.vickikbt.cache.models.CastEntity
import com.vickikbt.cache.models.MovieDetailsEntity
import com.vickikbt.cache.models.VideoEntity
import com.vickikbt.repository.mappers.toDomain
import com.vickikbt.domain.models.Cast
import com.vickikbt.domain.models.MovieDetails
import com.vickikbt.domain.models.SimilarResult
import com.vickikbt.domain.models.Video
import com.vickikbt.domain.repository.MovieDetailsRepository
import com.vickikbt.domain.utils.Constants.API_KEY
import com.vickikbt.network.ApiService
import com.vickikbt.repository.mappers.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieDetailsRepositoryImpl constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) : com.vickikbt.network.utils.SafeApiRequest(), MovieDetailsRepository {

    private val _movieDetails = MutableLiveData<MovieDetailsEntity>()
    private val _cast = MutableLiveData<CastEntity>()
    private val _videos = MutableLiveData<VideoEntity>()

    init {
        _movieDetails.observeForever { movieDetails ->
            //Coroutines.io { saveMovieDetails(movieDetails) }
        }

        _cast.observeForever { cast ->
            //Coroutines.io { saveMovieCast(cast) }
        }

        _videos.observeForever { videos ->
            //Coroutines.io { saveMovieVideos(videos) }
        }
    }

    /*override suspend fun saveMovieDetails(movieDetailsEntity: MovieDetailsEntity) =
        appDatabase.movieDetailsDao().saveMovieDetails(movieDetailsEntity)*/


    override suspend fun getMovieDetails(movieId: Int): Flow<MovieDetails> {
        val movieDetailsCacheResponse = appDatabase.movieDetailsDao().getMovieDetails(movieId)

        return if (movieDetailsCacheResponse != null) {
            flow { emit(movieDetailsCacheResponse.toDomain()) }
        } else {
            val movieDetailsNetworkResponse =
                safeApiRequest { apiService.fetchMovieDetails(movieId, API_KEY, "en") }

            _movieDetails.value = movieDetailsNetworkResponse.toEntity()

            flow { emit(movieDetailsNetworkResponse.toEntity().toDomain()) }
        }
    }

    /*override suspend fun saveMovieCast(castEntity: CastEntity) =
        appDatabase.castDao().saveMovieCast(castEntity)*/

    override suspend fun getMovieCast(movieId: Int): Flow<Cast> {
        val movieCastCacheResponse = appDatabase.castDao().getMovieCast(movieId)

        return if (movieCastCacheResponse != null) {
            flow { emit(movieCastCacheResponse.toDomain()) }
        } else {
            val movieCastNetworkResponse =
                safeApiRequest { apiService.fetchMovieCast(movieId, API_KEY, "en") }

            _cast.value = movieCastNetworkResponse.toEntity()

            flow { emit(movieCastNetworkResponse.toEntity().toDomain()) }
        }
    }

    /*override suspend fun saveMovieVideos(videoEntity: VideoEntity) =
        appDatabase.videosDao().saveMovieVideo(videoEntity)*/

    override suspend fun getMovieVideos(movieId: Int): Flow<Video> {
        val movieVideosCacheResponse = appDatabase.videosDao().getMovieVideo(movieId)

        return if (movieVideosCacheResponse != null) {
            flow { emit(movieVideosCacheResponse.toDomain()) }
        } else {
            val movieVideosNetworkResponse =
                safeApiRequest { apiService.fetchMovieVideos(movieId, API_KEY, "en") }

            _videos.value = movieVideosNetworkResponse.toEntity()

            flow { emit(movieVideosNetworkResponse.toEntity().toDomain()) }
        }
    }

    override suspend fun fetchSimilarMovies(movieId: Int): Flow<SimilarResult> {
        val similarMoviesDto = safeApiRequest { apiService.fetchSimilarMovies(movieId) }
        return flow { emit(similarMoviesDto.toEntity().toDomain()) }
    }


}