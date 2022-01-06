package com.vickikbt.repository.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.vickikbt.cache.AppDatabase
import com.vickikbt.domain.models.Movie
import com.vickikbt.network.ApiService

@ExperimentalPagingApi
class MoviesRemoteMediator constructor(
    private val category: String,
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) : RemoteMediator<Int, List<Movie>>() {

    private val moviesDao = appDatabase.moviesDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, List<Movie>>
    ): MediatorResult {
        TODO("Not yet implemented")
    }

}