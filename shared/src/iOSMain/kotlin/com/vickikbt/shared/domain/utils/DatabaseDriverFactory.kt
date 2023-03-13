package com.vickikbt.shared.domain.utils

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.vickikbt.shared.data.cache.sqldelight.AppDatabase

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(schema = AppDatabase.Schema, name = "notflix.db")
    }
}
