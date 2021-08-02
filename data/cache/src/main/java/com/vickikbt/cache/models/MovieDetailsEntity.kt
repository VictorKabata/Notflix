package com.vickikbt.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movie Details Table")
data class MovieDetailsEntity(

    val adult: Boolean?,

    val backdropPath: String?,

    val genres: List<GenreEntity>?,

    val homepage: String?,

    @PrimaryKey(autoGenerate = false)
    val id: Int?,

    val imdbId: String?,

    val originalLanguage: String?,

    val originalTitle: String?,

    val overview: String?,

    val popularity: Double?,

    val posterPath: String?,

    val releaseDate: String?,

    val runtime: Int?,

    val spokenLanguages: List<SpokenLanguageEntity>?,

    val status: String?,

    val tagline: String?,

    val title: String?,

    val video: Boolean?,

    val voteAverage: Double?,

    val voteCount: Int?
)