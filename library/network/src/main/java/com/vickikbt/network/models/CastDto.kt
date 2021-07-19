package com.vickikbt.network.models

import com.google.gson.annotations.SerializedName
import com.vickikbt.data.models.dto.CastItemDto


data class CastDto(
    @SerializedName("cast")
    val castItemDto: List<CastItemDto>?,

    @SerializedName("id")
    val id: Int
)