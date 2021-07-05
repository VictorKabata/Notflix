package com.vickikbt.network.models

import com.google.gson.annotations.SerializedName
import com.vickikbt.data.models.dto.MovieDto

data class UpcomingResultDto(
    @SerializedName("dates")
    val upcomingDates: UpcomingDatesDto?,

    @SerializedName("page")
    val page: Int?,

    @SerializedName("results")
    val moviesDto: List<MovieDto>?,

    @SerializedName("total_pages")
    val total_pages: Int,

    @SerializedName("total_results")
    val total_results: Int
)