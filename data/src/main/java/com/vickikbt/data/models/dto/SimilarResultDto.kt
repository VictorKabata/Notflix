package com.vickikbt.data.models.dto

import com.google.gson.annotations.SerializedName

data class SimilarResultDto(
    @SerializedName("page")
    val page: Int? = null,

    @SerializedName("results")
    val movieDtos: List<MovieDto>? = null,

    @SerializedName("total_pages")
    val total_pages: Int? = null,

    @SerializedName("total_results")
    val total_results: Int? = null
)