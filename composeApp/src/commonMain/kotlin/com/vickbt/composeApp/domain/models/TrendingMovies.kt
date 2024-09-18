package com.vickbt.composeApp.domain.models

data class TrendingMovies(
    val page: Int? = null,

    val movies: List<Movie>? = null,

    val totalPages: Int? = null,

    val totalResults: Int? = null
)
