package com.vickikbt.data.models.dto


import com.google.gson.annotations.SerializedName

data class CastItemDto(
    @SerializedName("adutlt")
    val adult: Boolean?,

    @SerializedName("cast_id")
    val castId: Int?,

    @SerializedName("character")
    val character: String?,

    @SerializedName("credit_id")
    val creditId: String?,

    @SerializedName("gender")
    val gender: Int?,

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String?,

    @SerializedName("order")
    val order: Int?,

    @SerializedName("original_name")
    val originalName: String?,

    @SerializedName("popularity")
    val popularity: Double?,

    @SerializedName("profile_path")
    val profilePath: String?
)