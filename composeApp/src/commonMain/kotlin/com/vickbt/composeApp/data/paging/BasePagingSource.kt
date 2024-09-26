package com.vickbt.composeApp.data.paging

import app.cash.paging.PagingSource
import app.cash.paging.PagingState
import com.vickbt.composeApp.domain.utils.Constants.STARTING_PAGE_INDEX

class BasePagingSource<T : Any>(val fetchData: suspend (page: Int) -> List<T>?) :
    PagingSource<Int, T>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val page = params.key ?: STARTING_PAGE_INDEX

        val data = fetchData(page) ?: emptyList()

        return try {
            LoadResult.Page(
                data = data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (data.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
