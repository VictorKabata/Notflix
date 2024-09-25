package com.vickbt.composeApp.domain.models

data class MovieDetails(

    val adult: Boolean? = null,

    val backdropPath: String? = null,

    val homepage: String? = null,

    val id: Int,

    val imdbId: String? = null,

    val originalLanguage: String? = null,

    val originalTitle: String,

    val overview: String,

    val popularity: Double? = null,

    val posterPath: String? = null,

    val releaseDate: String? = null,

    val runtime: Int? = null,

    val status: String? = null,

    val tagline: String? = null,

    val title: String,

    val video: Boolean? = null,

    val voteAverage: Double? = null,

    val voteCount: Int? = null
)
