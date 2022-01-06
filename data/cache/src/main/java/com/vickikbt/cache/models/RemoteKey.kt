package com.vickikbt.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Remote_Key_Table")
data class RemoteKey(
    @ColumnInfo(name = "Player_ID")
    @PrimaryKey(autoGenerate = false)
    val playerId: Int,

    @ColumnInfo(name = "Previous_Key")
    val prevKey: Int? = null,

    @ColumnInfo(name = "Next_Key")
    val nextKey: Int? = null
)