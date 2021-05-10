package com.vickikbt.data.repository

import com.vickikbt.domain.models.Movie
import com.vickikbt.domain.models.UpcomingDates
import com.vickikbt.domain.models.UpcomingResult
import com.vickikbt.domain.repositories.IUpcomingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class UpcomingMoviesRepositoryTest : IUpcomingRepository {

    private val upcomingDates = UpcomingDates("maximum", "minimum")
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
    private val upcomingResult = UpcomingResult(upcomingDates, 1, listOf(movie), 2, 100)


    override suspend fun fetchUpcomingMovies(): Flow<UpcomingResult> {
        return flowOf(upcomingResult)
    }


}