package com.vickikbt.shared.data.cache.sqldelight.adapters

import com.squareup.sqldelight.ColumnAdapter
import com.vickikbt.shared.data.cache.sqldelight.ActorEntity
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val castCustomAdapter = object : ColumnAdapter<List<ActorEntity>, String> {

    override fun decode(databaseValue: String): List<ActorEntity> {
        return if (databaseValue.isEmpty()) emptyList()
        else Json.decodeFromString(databaseValue)
    }

    override fun encode(value: List<ActorEntity>): String {
        return if (value == null) ""
        else Json.encodeToString(value)
    }
}
