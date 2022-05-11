package com.vickikbt.domain.models

data class SimilarMovies(

    val page: Int?,

    val movies: List<Movie>?,

    val totalPages: Int?,

    val totalResults: Int?
)
