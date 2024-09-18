package com.vickbt.composeApp.domain.models

data class UpcomingMovies(

    val dates: Dates? = null,

    val page: Int? = null,

    val movies: List<Movie>? = null,

    val totalPages: Int? = null,

    val totalResults: Int? = null
)
