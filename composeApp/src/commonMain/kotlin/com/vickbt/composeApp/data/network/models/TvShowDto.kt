package com.vickbt.composeApp.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TvShowDto(
    val adult: Boolean? = null,

    @SerialName("backdrop_path")
    val backdropPath: String? = null,

    val id: Int,

    val name: String? = null,

    val overview: String? = null,

    @SerialName("poster_path")
    val posterPath: String? = null,

    @SerialName("media_type")
    val mediaType: String? = null,

    @SerialName("genre_ids")
    val genreIds: List<Int>? = null,

    val popularity: Double? = null,

    @SerialName("first_air_date")
    val firstAirDate: String? = null,

    @SerialName("vote_average")
    val voteAverage: Double? = null,

    @SerialName("vote_count")
    val voteCount: Int? = null,

    @SerialName("origin_country")
    val originCountry: List<String>? = null,
)
