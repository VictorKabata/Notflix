package com.vickbt.composeApp.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieVideoDto(

    @SerialName("id")
    val id: Int? = null,

    @SerialName("results")
    val videos: List<VideoDto>? = null
)
