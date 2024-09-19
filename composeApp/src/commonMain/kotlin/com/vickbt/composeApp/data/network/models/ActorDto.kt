package com.vickbt.composeApp.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActorDto(

    @SerialName("cast_id")
    val castId: Int? = null,

    @SerialName("character")
    val character: String? = null,

    @SerialName("credit_id")
    val creditId: String? = null,

    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String? = null,

    @SerialName("original_name")
    val originalName: String? = null,

    @SerialName("profile_path")
    val profilePath: String? = null
)
