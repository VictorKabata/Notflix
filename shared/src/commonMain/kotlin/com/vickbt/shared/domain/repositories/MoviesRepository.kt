package com.vickbt.shared.domain.repositories

import com.vickbt.shared.domain.models.Category
import com.vickbt.shared.domain.models.Movie
import com.vickbt.shared.domain.utils.Constants.STARTING_PAGE_INDEX
import com.vickbt.shared.domain.utils.Enums
import com.vickbt.shared.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    /** Fetch Now Playing movies from data source*/
    suspend fun fetchHomePage(): Flow<ResultState<Map<Enums.MovieCategories, List<Movie?>>>>

    // Search movie from network source
    /*suspend fun searchMovie(
        movieName: String,
        page: Int = STARTING_PAGE_INDEX
    ): Flow<ResultState<List<Movie>?>>*/
}
