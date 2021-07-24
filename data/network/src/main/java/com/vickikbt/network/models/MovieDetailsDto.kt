package com.vickikbt.data.models.dto


import com.google.gson.annotations.SerializedName
import com.vickikbt.network.models.ProductionCompanyDto
import com.vickikbt.network.models.ProductionCountryDto

data class MovieDetailsDto(
    @SerializedName("adult")
    val adult: Boolean?,

    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @SerializedName("budget")
    val budget: Int?,

    @SerializedName("genres")
    val genreDtos: List<GenreDto>?,

    @SerializedName("homepage")
    val homepage: String?,

    @SerializedName("id")
    val id: Int,

    @SerializedName("imdb_id")
    val imdbId: String?,

    @SerializedName("original_language")
    val originalLanguage: String?,

    @SerializedName("original_title")
    val originalTitle: String?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("popularity")
    val popularity: Double?,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("production_companies")
    val productionCompanyDtos: List<ProductionCompanyDto>?,

    @SerializedName("production_countries")
    val productionCountryDtos: List<ProductionCountryDto>?,

    @SerializedName("release_date")
    val releaseDate: String?,

    @SerializedName("revenue")
    val revenue: Int?,

    @SerializedName("runtime")
    val runtime: Int?,

    @SerializedName("spoken_languages")
    val spokenLanguageDtos: List<SpokenLanguageDto>?,

    @SerializedName("status")
    val status: String?,

    @SerializedName("tagline")
    val tagline: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("video")
    val video: Boolean?,

    @SerializedName("vote_average")
    val voteAverage: Double?,

    @SerializedName("vote_count")
    val voteCount: Int?
)