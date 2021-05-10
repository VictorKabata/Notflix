package com.vickikbt.data.repository

import androidx.lifecycle.MutableLiveData
import com.vickikbt.data.models.entity.MovieEntity
import com.vickikbt.data.models.entity.PopularResultEntity
import com.vickikbt.domain.models.Movie
import com.vickikbt.domain.models.PopularResult
import com.vickikbt.domain.repositories.IPopularRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class PopularMoviesRepositoryTest:IPopularRepository {

    private val movie = Movie(
        true,
        "backdrop_url",
        listOf(1, 2),
        1,
        "original_language",
        "original_title",
        "overview",
        1.0,
        "poster_url",
        "release",
        "title",
        false,
        1.0,
        1
    )
    private val popularResult = PopularResult(1, listOf(movie), 2, 100)

    /*private val observableMovie=MutableLiveData(movie)
    private val observablePopularResult=MutableLiveData(popularResult)*/


    override suspend fun fetchPopularMovies(): Flow<PopularResult> {
        return flowOf(popularResult)
    }



}