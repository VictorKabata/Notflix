package com.vickbt.shared.data.cache.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Favorite Movie Table")
data class FavoriteMovieEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    val imdbId: String?,
    val backdropPath: String?,
    val posterPath: String?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val releaseDate: String?,
    val runTime: Int?,
    val status: String?,
    val tagLine: String?,
    val title: String?,
    val voteAverage: Double?,
    val voteCount: Int?,
    val createdAt: String?
)
