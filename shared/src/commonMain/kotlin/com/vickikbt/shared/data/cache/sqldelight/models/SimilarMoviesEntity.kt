package com.vickikbt.shared.data.cache.sqldelight.models

data class SimilarMoviesEntity(

    val page: Int?,

    val movies: List<com.vickikbt.shared.data.cache.sqldelight.MovieEntity>?,

    val totalPages: Int?,

    val totalResults: Int?
)
