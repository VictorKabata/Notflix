package com.vickikbt.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cast_Table")
data class CastEntity(
    @ColumnInfo(name = "Cast")
    val cast: List<CastItemEntity>?,

    @ColumnInfo(name = "ID")
    @PrimaryKey(autoGenerate = false)
    val id: Int
)