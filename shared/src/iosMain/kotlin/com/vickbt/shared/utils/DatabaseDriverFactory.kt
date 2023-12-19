package com.vickbt.shared.utils

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.vickbt.shared.AppDatabase

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver{
        return NativeSqliteDriver(AppDatabase.Schema, "Notflix.db")
    }
}
