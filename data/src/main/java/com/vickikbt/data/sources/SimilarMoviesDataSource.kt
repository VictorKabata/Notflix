package com.vickikbt.data.sources

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.vickikbt.data.cache.AppDatabase
import com.vickikbt.data.mappers.toDomain
import com.vickikbt.data.mappers.toEntity
import com.vickikbt.data.models.dto.MovieDto
import com.vickikbt.data.network.ApiService
import com.vickikbt.data.paging.SimilarMoviesPagingSource
import com.vickikbt.data.util.Constants.PAGING_SIZE
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

    fun fetchSimilarMovie(movieId: Int): Flow<PagingData<MovieDto>> {
        //Instantiates the paging factory
        val pagingSourceFactory =
            SimilarMoviesPagingSource(apiService = apiService, movieId = movieId)

        //Modify the behaviour of the pagination
        val pagingConfig = PagingConfig(
            pageSize = PAGING_SIZE,
            maxSize = PAGING_SIZE + (PAGING_SIZE * 2),
            enablePlaceholders = false
        )

        //returns a paging data with the defined config and paging factory
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { pagingSourceFactory }
        ).flow
    }

}