package com.vickbt.composeApp.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CastDto(

    @SerialName("cast")
    val actor: List<ActorDto>? = null,

    @SerialName("id")
    val id: Int
)
