package com.vickikbt.cache.models

data class NowPlayingMoviesEntity(

    val dates: DatesEntity?,

    val page: Int?,

    val movies: List<MovieEntity>?,

    val totalPages: Int?,

    val totalResults: Int?
)
