package com.vickikbt.data.models.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vickikbt.domain.models.Genre
import com.vickikbt.domain.models.ProductionCompany
import com.vickikbt.domain.models.ProductionCountry
import com.vickikbt.domain.models.SpokenLanguage

@Entity(tableName = "Movie Details Table")
data class MovieDetailsEntity(
    @ColumnInfo(name = "Adult")
    val adult: Boolean,

    @ColumnInfo(name = "Backdrop Path")
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

    @ColumnInfo(name = "IMDB ID")
    val imdbId: String,

    @ColumnInfo(name = "Original Language")
    val originalLanguage: String,

    @ColumnInfo(name = "Original Title")
    val originalTitle: String,

    @ColumnInfo(name = "Overview")
    val overview: String,

    @ColumnInfo(name = "Popularity")
    val popularity: Double,

    @ColumnInfo(name = "Poster Path")
    val posterPath: String,

    @Embedded(prefix = "Production Companies ")
    val productionCompanies: List<ProductionCompanyEntity>,

    @Embedded(prefix = "Production Country ")
    val productionCountries: List<ProductionCountryEntity>,

    @ColumnInfo(name = "Release Date")
    val releaseDate: String,

    @ColumnInfo(name = "Revenue")
    val revenue: Int,

    @ColumnInfo(name = "Runtime")
    val runtime: Int,

    @Embedded(prefix = "Spoken Languages")
    val spokenLanguages: List<SpokenLanguageEntity>,

    @ColumnInfo(name = "Status")
    val status: String,

    @ColumnInfo(name = "Tag Line")
    val tagLine: String,

    @ColumnInfo(name = "Title")
    val title: String,

    @ColumnInfo(name = "Video")
    val video: Boolean,

    @ColumnInfo(name = "Vote Average")
    val voteAverage: Double,

    @ColumnInfo(name = "Vote Count")
    val voteCount: Int
){

}