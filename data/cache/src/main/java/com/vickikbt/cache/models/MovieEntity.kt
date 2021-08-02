package com.vickikbt.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movies Table")
data class MovieEntity(
    val adult: Boolean?,

    val backdropPath: String?,

    val genreIds: List<Int>?,

    @PrimaryKey(autoGenerate = false)
    val id: Int,

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

    val isFavorite: Boolean?
)
