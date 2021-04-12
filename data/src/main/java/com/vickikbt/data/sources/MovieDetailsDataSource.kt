package com.vickikbt.data.sources

import com.vickikbt.data.cache.AppDatabase
import com.vickikbt.data.mappers.toDomain
import com.vickikbt.data.mappers.toEntity
import com.vickikbt.data.models.entity.MovieDetailsEntity
import com.vickikbt.data.network.ApiService
import com.vickikbt.data.util.Constants.API_KEY
import com.vickikbt.data.util.SafeApiRequest
import com.vickikbt.domain.models.Movie
import com.vickikbt.domain.models.MovieDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDetailsDataSource @Inject constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) : SafeApiRequest() {


    /* TODO: Query from local database
    suspend fun getMovieDetails(){
        //val movieDetails=appDatabase.
    }*/

    //TODO: Move API_KEY TO APIKEY.PROPERTIES
    //TODO: Make Language Param dynamic based on device language
    suspend fun fetchMovieDetails(movieId: Int): Flow<MovieDetails> {
        val movieResponse = safeApiRequest { apiService.fetchMovieDetails(movieId, API_KEY, "en") }
        return flow { emit(movieResponse.toEntity().toDomain()) }
    }

}