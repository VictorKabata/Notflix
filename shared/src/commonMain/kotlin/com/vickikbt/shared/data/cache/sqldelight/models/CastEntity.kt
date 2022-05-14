package com.vickikbt.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Casts Table")
data class CastEntity(

    val actor: List<ActorEntity>?,

    @PrimaryKey(autoGenerate = false)
    val id: Int?
)
