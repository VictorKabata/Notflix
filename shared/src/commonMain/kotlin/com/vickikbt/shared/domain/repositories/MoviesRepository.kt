package com.vickikbt.shared.domain.repositories

import com.vickikbt.shared.domain.models.Movie
import com.vickikbt.shared.domain.utils.Constants
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun fetchNowPlayingMovies(category: String = Constants.CATEGORY_NOW_PLAYING_MOVIES): Flow<List<Movie>?>

    suspend fun fetchMovies(category: String): Flow<List<Movie>?>
}
