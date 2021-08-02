package com.vickikbt.cache.models

data class SimilarMoviesEntity(

    val page: Int?,

    val movies: List<MovieEntity>?,

    val totalPages: Int?,

    val totalResults: Int?
)