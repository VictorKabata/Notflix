package com.vickikbt.repository.repository.movies_repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.vickikbt.cache.AppDatabase
import com.vickikbt.cache.models.MovieEntity
import com.vickikbt.domain.models.Movie
import com.vickikbt.domain.utils.Constants
import com.vickikbt.domain.utils.Coroutines
import com.vickikbt.network.ApiService
import com.vickikbt.network.utils.SafeApiRequest
import com.vickikbt.repository.mappers.toEntity
import com.vickikbt.repository.paging.MoviesRemoteMediator
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class MoviesRepositoryImpl constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) : MoviesRepository, SafeApiRequest() {
    private val moviesDao = appDatabase.moviesDao()

    private val _movieMutableLiveData = MutableLiveData<List<MovieEntity>>()

    init {
        _movieMutableLiveData.observeForever { movies ->
            Coroutines.io { saveMovies(movieEntities = movies) }
        }
    }

    private suspend fun saveMovies(movieEntities: List<MovieEntity>) =
        moviesDao.saveMovies(movieEntities)

    override suspend fun fetchNowPlayingMovies(category: String): Flow<List<Movie>> {
        val isCategoryCacheAvailable = moviesDao.isCategoryCacheAvailable(category) > 0

        return if (isCategoryCacheAvailable) {
            moviesDao.getNowPlayingMovies()
        } else {
            val networkResponse = safeApiRequest { apiService.fetchNowPlayingMovies() }.movies
            val nowPlayingMoviesEntity = networkResponse?.map { it.toEntity(category = category) }
            _movieMutableLiveData.value = nowPlayingMoviesEntity!!

            moviesDao.getNowPlayingMovies()
        }
    }

    override suspend fun fetchMovies(category: String): Flow<PagingData<Movie>> {
        val isCategoryCacheAvailable = moviesDao.isCategoryCacheAvailable(category) > 0

        val moviesPagingConfig = PagingConfig(
            enablePlaceholders = false,
            pageSize = Constants.PAGING_SIZE,
            maxSize = Constants.PAGING_SIZE + (Constants.PAGING_SIZE * 4)
        )

        val moviesRemoteMediator = MoviesRemoteMediator(
            category = category,
            apiService = apiService,
            appDatabase = appDatabase
        )
        val moviesPagingSource = { moviesDao.getMovies(category = category) }

        return Pager(
            config = moviesPagingConfig,
            remoteMediator = moviesRemoteMediator,
            pagingSourceFactory = moviesPagingSource
        ).flow
    }
}
