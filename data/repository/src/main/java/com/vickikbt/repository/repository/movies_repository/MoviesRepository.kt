package com.vickikbt.repository.repository.movies_repository

import androidx.paging.PagingData
import com.vickikbt.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun fetchMovies(category:String): Flow<PagingData<Movie>>

}