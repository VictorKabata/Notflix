package com.vickikbt.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Favorite Movies Table")
data class FavoriteMovieEntity(

    @ColumnInfo(name = "Adult")
    val adult: Boolean? ,

    @ColumnInfo(name = "Backdrop_Path")
    val backdrop_path: String? ,

    @ColumnInfo(name = "Genre_IDs")
    val genre_ids: List<Int>? ,

    @ColumnInfo(name = "ID")
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    @ColumnInfo(name = "Original_Language")
    val original_language: String? ,

    @ColumnInfo(name = "Original_Title")
    val original_title: String? ,

    @ColumnInfo(name = "Overview")
    val overview: String? ,

    @ColumnInfo(name = "Popularity")
    val popularity: Double? ,

    @ColumnInfo(name = "Poster_Path")
    val poster_path: String? ,

    @ColumnInfo(name = "Release_Date")
    val release_date: String? ,

    @ColumnInfo(name = "Title")
    val title: String? ,

    @ColumnInfo(name = "Video")
    val video: Boolean? ,

    @ColumnInfo(name = "Vote_Average")
    val vote_average: Double? ,

    @ColumnInfo(name = "Vote_Count")
    val vote_count: Int? 
)