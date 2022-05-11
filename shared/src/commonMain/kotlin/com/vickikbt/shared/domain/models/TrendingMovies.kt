package com.vickikbt.shared.domain.models

data class TrendingMovies(
    val page: Int?,

    val movies: List<Movie>?,

    val totalPages: Int?,

    val totalResults: Int?
)
