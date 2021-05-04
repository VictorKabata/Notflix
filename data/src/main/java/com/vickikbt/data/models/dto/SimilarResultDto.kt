package com.vickikbt.data.models.dto

import com.google.gson.annotations.SerializedName

data class SimilarResultDto(
    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val movieDtos: List<MovieDto>,

    @SerializedName("total_pages")
    val total_pages: Int,

    @SerializedName("total_results")
    val total_results: Int
)