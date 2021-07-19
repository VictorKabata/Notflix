package com.vickikbt.domain.models

data class SimilarResult(
    val page: Int? = null,

    val movies: List<Movie>? = null,

    val total_pages: Int? = null,

    val total_results: Int? = null
)