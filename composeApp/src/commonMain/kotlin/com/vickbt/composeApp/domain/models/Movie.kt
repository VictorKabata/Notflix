package com.vickbt.composeApp.domain.models

data class Movie(
    val adult: Boolean? = null,

    val backdropPath: String? = null,

    val id: Int,

    val originalLanguage: String? = null,

    val originalTitle: String? = null,

    val overview: String? = null,

    val popularity: Double? = null,

    val posterPath: String? = null,

    val releaseDate: String? = null,

    val title: String? = null,

    val video: Boolean? = null,

    val voteAverage: Double? = null,

    val voteCount: Int? = null,

    val category: String? = null,

    val isFavorite: Boolean? = null,

    val cacheId: Int? = 0,

    val mediaType: String? = null
)
