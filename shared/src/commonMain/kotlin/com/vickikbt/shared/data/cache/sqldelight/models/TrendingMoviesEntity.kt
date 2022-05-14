package com.vickikbt.cache.models

data class TrendingMoviesEntity(

    val page: Int?,

    val movies: List<MovieEntity>?,

    val totalPages: Int?,

    val totalResults: Int?
)
