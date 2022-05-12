package com.vickikbt.repository.repository.movies_repository

import androidx.paging.PagingData
import com.vickikbt.cache.models.MovieEntity
import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.domain.utils.Constants
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun fetchNowPlayingMovies(category: String = Constants.CATEGORY_NOW_PLAYING_MOVIES): Flow<List<Movie>>

    suspend fun fetchMovies(category: String): Flow<List<MovieEntity>>
}
