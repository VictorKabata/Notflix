package com.vickikbt.shared.domain.models

data class NowPlayingMovies(

    val dates: Dates?,

    val page: Int?,

    val movies: List<Movie>?,

    val totalPages: Int?,

    val totalResults: Int?
)
