package com.vickikbt.data.models.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movie_Details_Table")
data class MovieDetailsEntity(
    @ColumnInfo(name = "Adult")
    val adult: Boolean,

    @ColumnInfo(name = "Backdrop_Path")
    val backdrop_path: String,

    @ColumnInfo(name = "Budget")
    val budget: Int,

    @ColumnInfo(name = "Genres")
    val genresEntity: List<GenreEntity>,

    @ColumnInfo(name = "Homepage")
    val homepage: String,

    @ColumnInfo(name = "ID")
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    @ColumnInfo(name = "IMDB_ID")
    val imdb_id: String,

    @ColumnInfo(name = "Original_Language")
    val original_language: String,

    @ColumnInfo(name = "Original_Title")
    val original_title: String,

    @ColumnInfo(name = "Overview")
    val overview: String,

    @ColumnInfo(name = "Popularity")
    val popularity: Double,

    @ColumnInfo(name = "Poster_Path")
    val poster_path: String,

    @ColumnInfo(name = "Production_Companies")
    val productionCompaniesEntity: List<ProductionCompanyEntity>,

    @ColumnInfo(name = "Production_Country")
    val productionCountriesEntity: List<ProductionCountryEntity>,

    @ColumnInfo(name = "Release Date")
    val release_date: String,

    @ColumnInfo(name = "Revenue")
    val revenue: Int,

    @ColumnInfo(name = "Runtime")
    val runtime: Int,

    @ColumnInfo(name = "Spoken_Languages")
    val spokenLanguagesEntity: List<SpokenLanguageEntity>,

    @ColumnInfo(name = "Status")
    val status: String,

    @ColumnInfo(name = "Tagline")
    val tagline: String,

    @ColumnInfo(name = "Title")
    val title: String,

    @ColumnInfo(name = "Video")
    val video: Boolean,

    @ColumnInfo(name = "Vote_Average")
    val vote_average: Double,

    @ColumnInfo(name = "Vote_Count")
    val vote_count: Int
)