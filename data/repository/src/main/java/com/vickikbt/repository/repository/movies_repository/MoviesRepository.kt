package com.vickikbt.repository.repository.movies_repository

import androidx.paging.PagingData
import com.vickikbt.domain.models.Movie
import com.vickikbt.domain.utils.Constants
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun fetchNowPlayingMovies(category: String = Constants.CATEGORY_NOW_PLAYING_MOVIES): Flow<List<Movie>>

    suspend fun fetchMovies(category: String): Flow<PagingData<Movie>>
}
