package com.vickikbt.domain.models

import com.vickikbt.repository.models.Movie

data class PopularResult(
    val page: Int?,

    val movies: List<Movie>?,

    val total_pages: Int,

    val total_results: Int?
)