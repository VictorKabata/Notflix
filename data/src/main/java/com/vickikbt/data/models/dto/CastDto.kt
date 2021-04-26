package com.vickikbt.data.models.dto

import com.google.gson.annotations.SerializedName


data class CastDto(
    @SerializedName("cast")
    val castItemDto: List<CastItemDto>,

    @SerializedName("id")
    val id: Int
)