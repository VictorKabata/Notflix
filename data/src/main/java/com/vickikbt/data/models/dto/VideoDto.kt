package com.vickikbt.data.models.dto

import com.google.gson.annotations.SerializedName

data class VideoDto(

    @SerializedName("id")
    val id: Int,

    @SerializedName("results")
    val videoItemDtos: List<VideoItemDto>
)