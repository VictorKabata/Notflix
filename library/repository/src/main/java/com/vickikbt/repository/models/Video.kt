package com.vickikbt.repository.models

data class Video(
    val id: Int,
    val videoItems: List<VideoItem>?=null
)