package com.vickikbt.shared.domain.models

data class Movie(
    val adult: Boolean?,

    val backdropPath: String?,

    val id: Int?,

    val originalLanguage: String?,

    val originalTitle: String?,

    val overview: String?,

    val popularity: Double?,

    val posterPath: String?,

    val releaseDate: String?,

    val title: String?,

    val video: Boolean?,

    val voteAverage: Double?,

    val voteCount: Int?,

    val category: String?,

    val isFavorite: Boolean?,

    val cacheId: Int?,

    val mediaType: String?
)
