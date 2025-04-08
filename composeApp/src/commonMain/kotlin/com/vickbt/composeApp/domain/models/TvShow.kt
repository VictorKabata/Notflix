package com.vickbt.composeApp.domain.models

data class TvShow(
    val adult: Boolean? = null,
    val backdropPath: String? = null,
    val id: Int,
    val name: String? = null,
    val overview: String? = null,
    val posterPath: String? = null,
    val mediaType: String? = null,
    val genreIds: List<Int>? = null,
    val popularity: Double? = null,
    val firstAirDate: String? = null,
    val voteAverage: Double? = null,
    val voteCount: Int? = null,
    val originCountry: List<String>? = null,
)
