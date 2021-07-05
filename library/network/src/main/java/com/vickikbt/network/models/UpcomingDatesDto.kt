package com.vickikbt.network.models

import com.google.gson.annotations.SerializedName

data class UpcomingDatesDto(
    @SerializedName("maximum")
    val maximum: String?, //2021-04-22

    @SerializedName("minimum")
    val minimum: String? //2021-03-30
)