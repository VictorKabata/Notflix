package com.vickikbt.data.models.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movie_Details_Table")
data class MovieDetailsEntity(
    @ColumnInfo(name = "Adult")
    val adult: Boolean? = null,

    @ColumnInfo(name = "Backdrop_Path")
    val backdropPath: String? = null,

    @ColumnInfo(name = "Budget")
    val budget: Int? = null,

    @ColumnInfo(name = "Genres")
    val genresEntity: List<GenreEntity>? = null,

    @ColumnInfo(name = "Homepage")
    val homepage: String? = null,

    @ColumnInfo(name = "ID")
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    @ColumnInfo(name = "IMDB_ID")
    val imdbId: String? = null,

    @ColumnInfo(name = "Original_Language")
    val originalLanguage: String? = null,

    @ColumnInfo(name = "Original_Title")
    val originalTitle: String? = null,

    @ColumnInfo(name = "Overview")
    val overview: String? = null,

    @ColumnInfo(name = "Popularity")
    val popularity: Double? = null,

    @ColumnInfo(name = "Poster_Path")
    val posterPath: String? = null,

    @ColumnInfo(name = "Production_Companies")
    val productionCompaniesEntity: List<ProductionCompanyEntity>? = null,

    @ColumnInfo(name = "Production_Country")
    val productionCountriesEntity: List<ProductionCountryEntity>? = null,

    @ColumnInfo(name = "Release Date")
    val releaseDate: String? = null,

    @ColumnInfo(name = "Revenue")
    val revenue: Int? = null,

    @ColumnInfo(name = "Runtime")
    val runtime: Int? = null,

    @ColumnInfo(name = "Spoken_Languages")
    val spokenLanguagesEntity: List<SpokenLanguageEntity>? = null,

    @ColumnInfo(name = "Status")
    val status: String? = null,

    @ColumnInfo(name = "Tagline")
    val tagline: String? = null,

    @ColumnInfo(name = "Title")
    val title: String? = null,

    @ColumnInfo(name = "Video")
    val video: Boolean? = null,

    @ColumnInfo(name = "Vote_Average")
    val voteAverage: Double? = null,

    @ColumnInfo(name = "Vote_Count")
    val voteCount: Int? = null
)