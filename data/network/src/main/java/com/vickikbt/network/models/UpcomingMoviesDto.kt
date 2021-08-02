package com.vickikbt.network.models

import com.google.gson.annotations.SerializedName

data class UpcomingMoviesDto(

    val dates: DatesDto?,

    val page: Int?,

    @SerializedName("results")
    val movies: List<MovieDto>?,

    @SerializedName("total_pages")
    val totalPages: Int?,

    @SerializedName("total_results")
    val totalResults: Int?
)