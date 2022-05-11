package com.vickikbt.network.models

import com.google.gson.annotations.SerializedName

data class CastDto(

    @SerializedName("cast")
    val actor: List<ActorDto>?,

    val id: Int?
)
