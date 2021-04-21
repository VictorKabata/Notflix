package com.vickikbt.data.models.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movie Details Table")
data class MovieDetailsEntity(
    @ColumnInfo(name = "Adult")
    val adult: Boolean,

    @ColumnInfo(name = "Backdrop_Path")
    val backdropPath: String,

    @ColumnInfo(name = "Budget")
    val budget: Int,

    @Embedded(prefix = "Genre ")
    val genres: List<GenreEntity>,

    @ColumnInfo(name = "HomePage")
    val homepage: String,

    @ColumnInfo(name = "ID")
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    @ColumnInfo(name = "IMDB_ID")
    val imdbId: String,

    @ColumnInfo(name = "Original_Language")
    val originalLanguage: String,

    @ColumnInfo(name = "Original_Title")
    val originalTitle: String,

    @ColumnInfo(name = "Overview")
    val overview: String,

    @ColumnInfo(name = "Popularity")
    val popularity: Double,

    @ColumnInfo(name = "Poster_Path")
    val posterPath: String,

    @Embedded(prefix = "Production_Companies ")
    val productionCompanies: List<ProductionCompanyEntity>,

    @Embedded(prefix = "Production_Country ")
    val productionCountries: List<ProductionCountryEntity>,

    @ColumnInfo(name = "Release_Date")
    val releaseDate: String,

    @ColumnInfo(name = "Revenue")
    val revenue: Int,

    @ColumnInfo(name = "Runtime")
    val runtime: Int,

    @Embedded(prefix = "Spoken_Languages")
    val spokenLanguages: List<SpokenLanguageEntity>,

    @ColumnInfo(name = "Status")
    val status: String,

    @ColumnInfo(name = "Tag Line")
    val tagLine: String,

    @ColumnInfo(name = "Title")
    val title: String,

    @ColumnInfo(name = "Video")
    val video: Boolean,

    @ColumnInfo(name = "Vote_Average")
    val voteAverage: Double,

    @ColumnInfo(name = "Vote_Count")
    val voteCount: Int
)