package com.vickikbt.repository.data_source

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
import com.vickikbt.domain.repository.MovieDetailsRepository
import com.vickikbt.domain.utils.Coroutines
import com.vickikbt.network.ApiService
import com.vickikbt.network.utils.SafeApiRequest
import com.vickikbt.repository.mappers.toDomain
import com.vickikbt.repository.mappers.toEntity
import kotlinx.coroutines.flow.*

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
            Coroutines.io { saveMovieDetails(movieDetails.toDomain()) }
        }

        _cast.observeForever { cast ->
            Coroutines.io { saveMovieCast(cast.toDomain()) }
        }

        _videos.observeForever { videos ->
            Coroutines.io { saveMovieVideos(videos.toDomain()) }
        }
    }

    override suspend fun saveMovieDetails(movieDetails: MovieDetails) {
        appDatabase.movieDetailsDao().saveMovieDetails(movieDetails.toEntity())
    }

    override suspend fun getMovieDetails(movieId: Int): Flow<MovieDetails> {
        val isMovieDetailsCacheAvailable = movieDetailsDao.isMovieDetailsAvailable(movieId) > 0

        return try {
            if (isMovieDetailsCacheAvailable) {
                val movieDetailsCacheResponse = movieDetailsDao.getMovieDetails(movieId)
                movieDetailsCacheResponse.map { it.toDomain() }
            } else {
                val movieDetailsNetworkResponse =
                    safeApiRequest { apiService.fetchMovieDetails(movieId) }

                _movieDetails.value = movieDetailsNetworkResponse.toEntity()

                val movieDetailsCacheResponse = movieDetailsDao.getMovieDetails(movieId)
                movieDetailsCacheResponse.map { it.toDomain() }
            }
        } catch (e: Exception) {
            flowOf()
        }
    }

    override suspend fun saveMovieCast(cast: Cast) {
        appDatabase.castDao().saveMovieCast(cast.toEntity())
    }

    override suspend fun getMovieCast(movieId: Int): Flow<Cast> {
        val isMovieCacheAvailable = appDatabase.castDao().isMovieCastAvailable(movieId) > 0

        return try {
            if (isMovieCacheAvailable) {
                val movieCastCacheResponse = appDatabase.castDao().getMovieCast(movieId)
                movieCastCacheResponse.map { it.toDomain() }
            } else {
                val movieCastNetworkResponse =
                    safeApiRequest { apiService.fetchMovieCast(movieId) }

                Log.e("MovieDetails", "Movie Cast Network Response: $movieCastNetworkResponse")

                _cast.value = movieCastNetworkResponse.toEntity()

                val movieCastCacheResponse = appDatabase.castDao().getMovieCast(movieId)
                movieCastCacheResponse.map { it.toDomain() }
            }
        } catch (e: Exception) {
            Log.e("MovieDetails", "${e.message}")
            flowOf()
        }
    }

    override suspend fun saveMovieVideos(movieVideo: MovieVideo) {
        appDatabase.videosDao().saveMovieVideo(movieVideo.toEntity())
    }

    override suspend fun getMovieVideos(movieId: Int): Flow<MovieVideo> {
        val isMovieVideoCacheAvailable =
            appDatabase.videosDao().isMovieVideoCacheAvailable(movieId) > 0

        return try {
            if (isMovieVideoCacheAvailable) {
                val movieVideosCacheResponse = appDatabase.videosDao().getMovieVideo(movieId)
                movieVideosCacheResponse.map { it.toDomain() }
            } else {
                val movieVideosNetworkResponse =
                    safeApiRequest { apiService.fetchMovieVideos(movieId) }

                _videos.value = movieVideosNetworkResponse.toEntity()

                flow { emit(movieVideosNetworkResponse.toEntity().toDomain()) }
            }
        } catch (e: Exception) {
            Log.e("MovieDetails", "${e.message}")
            flowOf()
        }
    }

    override suspend fun fetchSimilarMovies(movieId: Int): Flow<SimilarMovies> {
        val similarMoviesDto = safeApiRequest { apiService.fetchSimilarMovies(movieId) }
        return try {
            flow { emit(similarMoviesDto.toEntity().toDomain()) }
        } catch (e: Exception) {
            Log.e("MovieDetails", "${e.message}")
            flowOf()
        }
    }

    override suspend fun isMovieFavorite(movieId: Int): Flow<Boolean?> {
        appDatabase.moviesDao().isMovieFavorite(movieId).collect {
            Log.d("Movdet", "Value of isFavorite is: ${it.toString()}")
        }
//        appDatabase.moviesDao().getFavoriteMovies().collect {
//            Log.d("Movdet", (it.size.toString()))
//        }
        return appDatabase.moviesDao().isMovieFavorite(movieId)
    }

    override suspend fun updateMovieIsFavorite(cacheId: Int, isFavorite: Boolean) =
        appDatabase.moviesDao().updateMovieIsFavorite(cacheId, isFavorite)
}
