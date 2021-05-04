package com.vickikbt.data.sources

import com.vickikbt.data.cache.AppDatabase
import com.vickikbt.data.mappers.toDomain
import com.vickikbt.data.mappers.toEntity
import com.vickikbt.data.network.ApiService
import com.vickikbt.data.util.SafeApiRequest
import com.vickikbt.domain.models.SimilarResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SimilarMoviesDataSource @Inject constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) : SafeApiRequest() {

    suspend fun fetchSimilarMovies(movieId: Int): Flow<SimilarResult> {
        val similarMoviesDto = safeApiRequest { apiService.fetchSimilarMovies(movieId) }
        return flow { emit(similarMoviesDto.toEntity().toDomain()) }
    }

}