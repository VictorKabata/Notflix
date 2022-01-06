package com.vickikbt.repository.repository.movies_repository

import com.vickikbt.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun fetchMovies(category:String): Flow<List<Movie>>

}