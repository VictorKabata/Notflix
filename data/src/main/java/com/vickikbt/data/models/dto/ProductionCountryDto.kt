package com.vickikbt.data.models.dto


import com.google.gson.annotations.SerializedName

data class ProductionCountryDto(
    @SerializedName("iso_3166_1")
    val iso31661: String,

    @SerializedName("name")
    val name: String
)