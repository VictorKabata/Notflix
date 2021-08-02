package com.vickikbt.network.models

import com.google.gson.annotations.SerializedName

data class ActorDto(

    @SerializedName("cast_id")
    val castId: Int?,

    val character: String?,

    @SerializedName("credit_id")
    val creditId: String?,

    val id: Int?,

    val name: String?,

    @SerializedName("original_name")
    val originalName: String?,

    @SerializedName("profile_path")
    val profilePath: String?
)
