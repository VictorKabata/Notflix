package com.vickikbt.shared.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NowPlayingMoviesDto(

    @SerialName("dates")
    val dates: DatesDto? = null,

    @SerialName("page")
    val page: Int? = null,

    @SerialName("results")
    val movies: List<MovieDto>? = null,

    @SerialName("total_pages")
    val totalPages: Int? = null,

    @SerialName("total_results")
    val totalResults: Int? = null
)
