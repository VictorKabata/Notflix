package com.vickikbt.repository.repository.movie_details_repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.vickikbt.cache.AppDatabase
import com.vickikbt.cache.models.CastEntity
import com.vickikbt.cache.models.MovieDetailsEntity
import com.vickikbt.cache.models.MovieVideoEntity
import com.vickikbt.shared.domain.utils.Coroutines
import com.vickikbt.network.utils.SafeApiRequest
import com.vickikbt.shared.data.mappers.toDomain
import com.vickikbt.shared.data.mappers.toEntity
import com.vickikbt.shared.data.network.ApiService
import com.vickikbt.shared.domain.repositories.MovieDetailsRepository
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
            Coroutines.default { saveMovieDetails(com.vickikbt.shared.data.mappers.toDomain()) }
        }

        _cast.observeForever { cast ->
            Coroutines.default { saveMovieCast(com.vickikbt.shared.data.mappers.toDomain()) }
        }

        _videos.observeForever { videos ->
            Coroutines.default { saveMovieVideos(com.vickikbt.shared.data.mappers.toDomain()) }
        }
    }

    override suspend fun saveMovieDetails(movieDetails: com.vickikbt.shared.domain.models.MovieDetails) {
        appDatabase.movieDetailsDao().saveMovieDetails(movieDetails.toEntity())
    }

    override suspend fun getMovieDetails(movieId: Int): Flow<com.vickikbt.shared.domain.models.MovieDetails> {
        val isMovieDetailsCacheAvailable = movieDetailsDao.isMovieDetailsAvailable(movieId) > 0

        return try {
            if (isMovieDetailsCacheAvailable) {
                val movieDetailsCacheResponse = movieDetailsDao.getMovieDetails(movieId)
                movieDetailsCacheResponse.map { com.vickikbt.shared.data.mappers.toDomain() }
            } else {
                val movieDetailsNetworkResponse = apiService.fetchMovieDetails(movieId)

                _movieDetails.value = movieDetailsNetworkResponse?.toEntity()

                val movieDetailsCacheResponse = movieDetailsDao.getMovieDetails(movieId)
                movieDetailsCacheResponse.map { com.vickikbt.shared.data.mappers.toDomain() }
            }
        } catch (e: Exception) {
            flowOf()
        }
    }

    override suspend fun saveMovieCast(cast: com.vickikbt.shared.domain.models.Cast) {
        appDatabase.castDao().saveMovieCast(cast.toEntity())
    }

    override suspend fun getMovieCast(movieId: Int): Flow<com.vickikbt.shared.domain.models.Cast> {
        val isMovieCacheAvailable = appDatabase.castDao().isMovieCastAvailable(movieId) > 0

        return try {
            if (isMovieCacheAvailable) {
                val movieCastCacheResponse = appDatabase.castDao().getMovieCast(movieId)
                movieCastCacheResponse.map { com.vickikbt.shared.data.mappers.toDomain() }
            } else {
                val movieCastNetworkResponse = apiService.fetchMovieCast(movieId)

                Log.e("MovieDetails", "Movie Cast Network Response: $movieCastNetworkResponse")

                _cast.value = movieCastNetworkResponse?.toEntity()

                val movieCastCacheResponse = appDatabase.castDao().getMovieCast(movieId)
                movieCastCacheResponse.map { com.vickikbt.shared.data.mappers.toDomain() }
            }
        } catch (e: Exception) {
            Log.e("MovieDetails", "${e.message}")
            flowOf()
        }
    }

    override suspend fun saveMovieVideos(movieVideo: com.vickikbt.shared.domain.models.MovieVideo) {
        appDatabase.videosDao().saveMovieVideo(movieVideo.toEntity())
    }

    override suspend fun getMovieVideos(movieId: Int): Flow<com.vickikbt.shared.domain.models.MovieVideo> {
        val isMovieVideoCacheAvailable =
            appDatabase.videosDao().isMovieVideoCacheAvailable(movieId) > 0

        return try {
            if (isMovieVideoCacheAvailable) {
                val movieVideosCacheResponse = appDatabase.videosDao().getMovieVideo(movieId)
                movieVideosCacheResponse.map { com.vickikbt.shared.data.mappers.toDomain() }
            } else {
                val movieVideosNetworkResponse = apiService.fetchMovieVideos(movieId)

                _videos.value = movieVideosNetworkResponse?.toEntity()

                flow { emit(movieVideosNetworkResponse?.toEntity()!!.toDomain()) }
            }
        } catch (e: Exception) {
            Log.e("MovieDetails", "${e.message}")
            flowOf()
        }
    }

    override suspend fun fetchSimilarMovies(movieId: Int): Flow<com.vickikbt.shared.domain.models.SimilarMovies> {
        val similarMoviesDto = apiService.fetchSimilarMovies(movieId)
        return try {
            flow { emit(similarMoviesDto?.toEntity()!!.toDomain()) }
        } catch (e: Exception) {
            Log.e("MovieDetails", "${e.message}")
            flowOf()
        }
    }

    override suspend fun isMovieFavorite(movieId: Int): Flow<Boolean?> {
        appDatabase.moviesDao().isMovieFavorite(movieId).collect {
            Log.d("Movdet", "Value of isFavorite is: $it")
        }
//        appDatabase.moviesDao().getFavoriteMovies().collect {
//            Log.d("Movdet", (it.size.toString()))
//        }
        return appDatabase.moviesDao().isMovieFavorite(movieId)
    }

    override suspend fun updateMovieIsFavorite(cacheId: Int, isFavorite: Boolean) =
        appDatabase.moviesDao().updateMovieIsFavorite(cacheId, isFavorite)
}
