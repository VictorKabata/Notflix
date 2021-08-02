package com.vickikbt.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movie Videos Table")
data class MovieVideoEntity(

    @PrimaryKey(autoGenerate = false)
    val id: Int?,

    val videos: List<VideoEntity>?
)