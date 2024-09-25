package com.vickbt.composeApp.domain.repositories

import app.cash.paging.PagingData
import com.vickbt.composeApp.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    // Search movie from network source
    suspend fun searchMovie(movieName: String): Result<Flow<PagingData<Movie>>>
}
