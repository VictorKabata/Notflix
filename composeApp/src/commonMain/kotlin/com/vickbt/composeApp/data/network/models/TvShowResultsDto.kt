package com.vickbt.composeApp.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TvShowResultsDto(
    @SerialName("page")
    val page: Int? = null,

    @SerialName("results")
    val tvShows: List<TvShowDto>? = null,

    @SerialName("total_pages")
    val totalPages: Int? = null,

    @SerialName("total_results")
    val totalResults: Int? = null
)
