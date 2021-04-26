package com.vickikbt.data.models.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "Cast_Table")
data class CastEntity(
    @ColumnInfo(name = "Cast")
    val cast: List<CastItemEntity>,

    @ColumnInfo(name = "ID")
    val id: Int
)