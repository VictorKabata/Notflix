package com.vickikbt.network.models

import com.google.gson.annotations.SerializedName

data class MovieVideoDto(

    val id: Int?,

    @SerializedName("results")
    val videos: List<VideoDto>?
)
