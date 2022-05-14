package com.vickikbt.shared.data.cache.sqldelight.models

import com.vickikbt.shared.data.cache.sqldelight.MovieEntity

data class PopularMoviesEntity(

    val page: Int?,

    val movies: List<MovieEntity>?,

    val totalPages: Int?,

    val totalResults: Int?
)
