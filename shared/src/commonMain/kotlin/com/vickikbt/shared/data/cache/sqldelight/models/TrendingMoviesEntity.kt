package com.vickikbt.cache.models

import com.vickikbt.shared.data.cache.sqldelight.MovieEntity

data class TrendingMoviesEntity(

    val page: Int?,

    val movies: List<MovieEntity>?,

    val totalPages: Int?,

    val totalResults: Int?
)
