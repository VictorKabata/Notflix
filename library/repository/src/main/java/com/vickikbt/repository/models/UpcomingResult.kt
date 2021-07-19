package com.vickikbt.domain.models

import com.vickikbt.repository.models.Movie
import com.vickikbt.repository.models.UpcomingDates

data class UpcomingResult(
    val upcomingDates: UpcomingDates?,

    val page: Int?,

    val movies: List<Movie>?,

    val total_pages: Int,

    val total_results: Int?
)