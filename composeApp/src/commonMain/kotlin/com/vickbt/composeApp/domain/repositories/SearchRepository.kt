package com.vickbt.composeApp.domain.repositories

import com.vickbt.composeApp.domain.models.Movie
import com.vickbt.composeApp.domain.utils.Constants.STARTING_PAGE_INDEX
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    // Search movie from network source
    suspend fun searchMovie(
        movieName: String,
        page: Int = STARTING_PAGE_INDEX
    ): Flow<Result<List<Movie>?>>
}
