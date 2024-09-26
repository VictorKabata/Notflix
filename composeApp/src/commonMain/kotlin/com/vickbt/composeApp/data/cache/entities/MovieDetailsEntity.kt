package com.vickbt.composeApp.data.cache.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Clock

@Entity(tableName = "Favorite Movies")
data class MovieDetailsEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val imdbId: String? = null,
    val backdropPath: String,
    val posterPath: String,
    val originalLanguage: String? = null,
    val originalTitle: String,
    val overview: String,
    val popularity: Double? = null,
    val releaseDate: String? = null,
    val runtime: Int? = null,
    val status: String? = null,
    val tagLine: String? = null,
    val title: String,
    val voteAverage: Double? = null,
    val voteCount: Int? = null,
    val createdAt: String = Clock.System.now().toEpochMilliseconds().toString()
)
