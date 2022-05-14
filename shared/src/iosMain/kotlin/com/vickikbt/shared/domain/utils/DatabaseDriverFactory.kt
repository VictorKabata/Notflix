package com.vickikbt.shared.domain.utils

import com.squareup.sqldelight.db.SqlDriver
import com.vickikbt.devtyme.data.cache.sqldelight.AppDatabase
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(schema = AppDatabase.Schema, name = "DevTyme.db")
    }
}
