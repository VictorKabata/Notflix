package com.vickikbt.domain.models

data class UpcomingResult(
    val upcomingDates: UpcomingDates?=null,

    val page: Int?=null,

    val movies: List<Movie>?=null,

    val total_pages: Int?=null,

    val total_results: Int?=null
)