package com.vickikbt.data.models.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movies Table")
data class MovieEntity(

    @ColumnInfo(name = "Adult")
    val adult: Boolean? = null,

    @ColumnInfo(name = "Backdrop_Path")
    val backdrop_path: String? = null,

    @ColumnInfo(name = "Genre_IDs")
    val genre_ids: List<Int>? = null,

    @ColumnInfo(name = "ID")
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    @ColumnInfo(name = "Original_Language")
    val original_language: String? = null,

    @ColumnInfo(name = "Original_Title")
    val original_title: String? = null,

    @ColumnInfo(name = "Overview")
    val overview: String? = null,

    @ColumnInfo(name = "Popularity")
    val popularity: Double? = null,

    @ColumnInfo(name = "Poster_Path")
    val poster_path: String? = null,

    @ColumnInfo(name = "Release_Date")
    val release_date: String? = null,

    @ColumnInfo(name = "Title")
    val title: String? = null,

    @ColumnInfo(name = "Video")
    val video: Boolean? = null,

    @ColumnInfo(name = "Vote_Average")
    val vote_average: Double? = null,

    @ColumnInfo(name = "Vote_Count")
    val vote_count: Int? = null
)