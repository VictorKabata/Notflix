package com.vickikbt.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movie_Details_Table")
data class MovieDetailsEntity(
    @ColumnInfo(name = "Adult")
    val adult: Boolean?,

    @ColumnInfo(name = "Backdrop_Path")
    val backdropPath: String?,

    @ColumnInfo(name = "Budget")
    val budget: Int?,

    @ColumnInfo(name = "Genres")
    val genresEntity: List<GenreEntity>?,

    @ColumnInfo(name = "Homepage")
    val homepage: String?,

    @ColumnInfo(name = "ID")
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    @ColumnInfo(name = "IMDB_ID")
    val imdbId: String?,

    @ColumnInfo(name = "Original_Language")
    val originalLanguage: String?,

    @ColumnInfo(name = "Original_Title")
    val originalTitle: String?,

    @ColumnInfo(name = "Overview")
    val overview: String?,

    @ColumnInfo(name = "Popularity")
    val popularity: Double?,

    @ColumnInfo(name = "Poster_Path")
    val posterPath: String?,

    @ColumnInfo(name = "Production_Companies")
    val productionCompaniesEntity: List<ProductionCompanyEntity>?,

    @ColumnInfo(name = "Production_Country")
    val productionCountriesEntity: List<ProductionCountryEntity>?,

    @ColumnInfo(name = "Release Date")
    val releaseDate: String?,

    @ColumnInfo(name = "Revenue")
    val revenue: Int?,

    @ColumnInfo(name = "Runtime")
    val runtime: Int?,

    @ColumnInfo(name = "Spoken_Languages")
    val spokenLanguagesEntity: List<SpokenLanguageEntity>?,

    @ColumnInfo(name = "Status")
    val status: String?,

    @ColumnInfo(name = "Tagline")
    val tagline: String?,

    @ColumnInfo(name = "Title")
    val title: String?,

    @ColumnInfo(name = "Video")
    val video: Boolean?,

    @ColumnInfo(name = "Vote_Average")
    val voteAverage: Double?,

    @ColumnInfo(name = "Vote_Count")
    val voteCount: Int? 
)