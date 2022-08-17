package com.vickikbt.shared.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResultsDto(
    @SerialName("page")
    val page: Int? = null,

    @SerialName("results")
    val movies: List<MovieDto>,

    @SerialName("total_pages")
    val totalPages: Int,

    @SerialName("total_results")
    val totalResults: Int? = null
)
