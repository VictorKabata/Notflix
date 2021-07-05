package com.vickikbt.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vickikbt.data.models.dto.MovieDto
import com.vickikbt.data.network.ApiService
import com.vickikbt.data.util.Constants.STARTING_PAGE_INDEX

class SimilarMoviesPagingSource constructor(
    private val apiService: ApiService,
    private val movieId: Int
) : PagingSource<Int, MovieDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDto> {
        val nextPageNumber = params.key ?: STARTING_PAGE_INDEX

        return try {
            val similarMoviesResponse =
                apiService.fetchSimilarMovies(movieId = movieId, page = nextPageNumber).body()!!

            LoadResult.Page(
                data = similarMoviesResponse.movieDtos!!,
                prevKey = if (nextPageNumber == STARTING_PAGE_INDEX) null else nextPageNumber - 1,
                nextKey = if (similarMoviesResponse.movieDtos.isEmpty()) null else nextPageNumber + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }


}