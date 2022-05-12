package com.vickikbt.repository.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.vickikbt.cache.AppDatabase
import com.vickikbt.cache.models.MovieEntity
import com.vickikbt.cache.models.RemoteKey
import com.vickikbt.shared.domain.utils.Constants
import com.vickikbt.shared.domain.utils.Constants.STARTING_PAGE_INDEX
import com.vickikbt.shared.data.network.models.PopularMoviesDto
import com.vickikbt.shared.data.network.models.TrendingMoviesDto
import com.vickikbt.shared.data.network.models.UpcomingMoviesDto
import com.vickikbt.repository.mappers.toEntity
import com.vickikbt.shared.data.network.ApiService
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class MoviesRemoteMediator constructor(
    private val category: String,
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) : RemoteMediator<Int, MovieEntity>() {

    private val moviesDao = appDatabase.moviesDao()
    private val remoteKeyDao = appDatabase.remoteKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        val pageKeyData = getPagedData(loadType, state)

        val page = when (pageKeyData) {
            is MediatorResult.Success -> {
                return pageKeyData
            }
            else -> {
                pageKeyData as Int
            }
        }

        try {
            val response = when (category) {
                Constants.CATEGORY_UPCOMING_MOVIES -> {
                    apiService.fetchUpcomingMovies(page = page)
                }
                Constants.CATEGORY_POPULAR_MOVIES -> {
                    apiService.fetchPopularMovies(page = page)
                }
                Constants.CATEGORY_TRENDING_MOVIES -> {
                    apiService.fetchTrendingMovies(page = page)
                }
                else -> null
            }

            val movies = when (category) {
                Constants.CATEGORY_UPCOMING_MOVIES -> {
                    (response as UpcomingMoviesDto).movies
                }
                Constants.CATEGORY_POPULAR_MOVIES -> {
                    (response as PopularMoviesDto).movies
                }
                Constants.CATEGORY_TRENDING_MOVIES -> {
                    (response as TrendingMoviesDto).movies
                }
                else -> null
            }

            val isEndOfList = when (category) {
                Constants.CATEGORY_UPCOMING_MOVIES -> {
                    movies?.isEmpty()
                }
                Constants.CATEGORY_POPULAR_MOVIES -> {
                    movies?.isEmpty()
                }
                Constants.CATEGORY_TRENDING_MOVIES -> {
                    movies?.isEmpty()
                }
                else -> true
            }

            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeyDao.deleteRemoteKeys()
                    moviesDao.deleteMovies(category = category)
                }

                val prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (isEndOfList!!) null else page + 1
                val keys = movies?.map {
                    RemoteKey(movieId = it.id!!, prevKey = prevKey, nextKey = nextKey)
                }

                remoteKeyDao.saveRemoteKeys(remoteKeys = keys!!)
                moviesDao.saveMovies(movieEntities = movies.map { it.toEntity(category = category) })
            }

            return when (category) {
                Constants.CATEGORY_UPCOMING_MOVIES -> {
                    MediatorResult.Success(endOfPaginationReached = false)
                }
                Constants.CATEGORY_POPULAR_MOVIES -> {
                    MediatorResult.Success(endOfPaginationReached = false)
                }
                Constants.CATEGORY_TRENDING_MOVIES -> {
                    MediatorResult.Success(endOfPaginationReached = false)
                }
                else -> MediatorResult.Success(endOfPaginationReached = true)
            }
        } catch (exception: IOException) {
            val error = IOException("Please Check Internet Connection")
            return MediatorResult.Error(error)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    // ToDo: Remove log statements
    private suspend fun getPagedData(
        loadType: LoadType,
        pagingState: PagingState<Int, MovieEntity>
    ): Any {
        return when (loadType) {
            LoadType.REFRESH -> {
                val remoteKey = getRemoteKeyClosestToCurrentPosition(pagingState)
                remoteKey?.nextKey?.minus(1) ?: STARTING_PAGE_INDEX
            }

            LoadType.APPEND -> {
                val remoteKey = getLastRemoteKey(pagingState)
                val nextKey = remoteKey?.nextKey

                nextKey ?: MediatorResult.Success(endOfPaginationReached = false)
            }

            LoadType.PREPEND -> {
                val remoteKey = getFirstRemoteKey(pagingState)
                val prevKey = remoteKey?.prevKey

                prevKey ?: MediatorResult.Success(endOfPaginationReached = false)
            }
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(pagingState: PagingState<Int, MovieEntity>): RemoteKey? {
        return pagingState.anchorPosition?.let { position ->
            pagingState.closestItemToPosition(position)?.id?.let { movieId ->
                appDatabase.remoteKeyDao().getRemoteKey(movieId = movieId)
            }
        }
    }

    private suspend fun getLastRemoteKey(pagingState: PagingState<Int, MovieEntity>): RemoteKey? {
        return pagingState.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { movie -> appDatabase.remoteKeyDao().getRemoteKey(movieId = movie.id!!) }
    }

    private suspend fun getFirstRemoteKey(pagingState: PagingState<Int, MovieEntity>): RemoteKey? {
        return pagingState.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { movie -> appDatabase.remoteKeyDao().getRemoteKey(movieId = movie.id!!) }
    }
}
