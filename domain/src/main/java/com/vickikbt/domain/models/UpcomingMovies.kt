package com.vickikbt.domain.models

data class UpcomingMovies(

    val dates: Dates?,

    val page: Int?,

    val movies: List<Movie>?,

    val totalPages: Int?,

    val totalResults: Int?
)
