package com.vickikbt.shared.data.data_sources

import com.vickikbt.shared.data.cache.sqldelight.MovieDetailsEntity
import com.vickikbt.shared.data.cache.sqldelight.daos.MovieDetailsDao
import com.vickikbt.shared.data.cache.sqldelight.daos.MoviesDao
import com.vickikbt.shared.data.mappers.toDomain
import com.vickikbt.shared.data.mappers.toEntity
import com.vickikbt.shared.data.network.ApiService
import com.vickikbt.shared.domain.models.Cast
import com.vickikbt.shared.domain.models.MovieDetails
import com.vickikbt.shared.domain.models.MovieVideo
import com.vickikbt.shared.domain.models.SimilarMovies
import com.vickikbt.shared.domain.repositories.MovieDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MovieDetailsRepositoryImpl constructor(
    private val apiService: ApiService,
    private val moviesDao: MoviesDao,
    private val movieDetailsDao: MovieDetailsDao
) : MovieDetailsRepository {

    override suspend fun saveMovieDetails(movieDetailsEntity: MovieDetailsEntity) {
        movieDetailsDao.saveMovieDetails(movieDetailsEntity = movieDetailsEntity)
    }

    override suspend fun getMovieDetails(movieId: Int): Flow<MovieDetails?> {
        val isMovieDetailsCacheAvailable =
            (movieDetailsDao.isMovieDetailsAvailable(movieId)?.toInt() ?: 0) > 0
        val movieDetailsNetworkResponse = apiService.fetchMovieDetails(movieId)

        return flowOf(movieDetailsNetworkResponse?.toEntity()?.toDomain())
    }

    override suspend fun saveMovieCast(cast: Cast) {
        // appDatabase.castDao().saveMovieCast(cast.toEntity())
    }

    override suspend fun getMovieCast(movieId: Int): Flow<Cast?> {
        val isMovieCacheAvailable =
            (movieDetailsDao.isMovieDetailsAvailable(movieId)?.toInt() ?: 0) > 0
        val movieCastNetworkResponse = apiService.fetchMovieCast(movieId)

        return flowOf(movieCastNetworkResponse?.toEntity()?.toDomain())
    }

    override suspend fun saveMovieVideos(movieVideo: MovieVideo) {
        // appDatabase.videosDao().saveMovieVideo(movieVideo.toEntity())
    }

    override suspend fun getMovieVideos(movieId: Int): Flow<MovieVideo?> {
        val isMovieCacheAvailable =
            (movieDetailsDao.isMovieDetailsAvailable(movieId)?.toInt() ?: 0) > 0
        val movieVideosNetworkResponse = apiService.fetchMovieVideos(movieId)

        return flowOf(movieVideosNetworkResponse?.toEntity()?.toDomain())
    }

    override suspend fun fetchSimilarMovies(movieId: Int): Flow<SimilarMovies?> {
        val similarMoviesDto = apiService.fetchSimilarMovies(movieId)
        return flowOf(similarMoviesDto?.toEntity()?.toDomain())
    }

    override suspend fun isMovieFavorite(movieId: Int): Flow<Boolean?> {
        return moviesDao.isMovieFavorite(movieId = movieId)
    }

    override suspend fun updateMovieIsFavorite(cacheId: Int, isFavourite: Boolean) =
        moviesDao.updateIsMovieFavorite(cacheId = cacheId, isFavourite = isFavourite)
}
