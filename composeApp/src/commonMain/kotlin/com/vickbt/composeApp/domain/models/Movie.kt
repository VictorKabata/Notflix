package com.vickbt.composeApp.domain.models

data class Movie(
    val adult: Boolean? = null,

    val backdropPath: String,

    val id: Int,

    val originalLanguage: String? = null,

    val originalTitle: String,

    val overview: String,

    val popularity: Double? = null,

    val posterPath: String,

    val releaseDate: String? = null,

    val title: String,

    val video: Boolean? = null,

    val voteAverage: Double? = null,

    val voteCount: Int? = null,

    val category: String? = null,

    val isFavorite: Boolean? = null,

    val cacheId: Int? = 0,

    val mediaType: String? = null
)
