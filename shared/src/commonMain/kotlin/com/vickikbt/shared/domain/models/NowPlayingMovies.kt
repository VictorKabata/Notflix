package com.vickikbt.shared.domain.models

data class NowPlayingMovies(

    val dates: Dates? = null,

    val page: Int? = null,

    val movies: List<Movie>? = null,

    val totalPages: Int? = null,

    val totalResults: Int? = null
)
