package com.vickbt.shared.domain.models

class Movie(
    val id: String,
    val title: String,
    val overview: String = "",
    val released: String? = null,
    val runtime: Int? = null,
    val youtubeTrailerId: String? = null,
    val quality: String? = null,
    val rating: Double? = null,
    val poster: String? = null,
    val banner: String? = null,
    val seasons: List<Season> = listOf(),
    val genres: List<Genre> = listOf(),
    val directors: List<People> = listOf(),
    val cast: List<People> = listOf(),
    val recommendations: List<Movie> = listOf(),
    val category: String
)
