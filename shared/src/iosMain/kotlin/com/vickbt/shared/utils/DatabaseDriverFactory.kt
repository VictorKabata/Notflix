package com.vickbt.shared.utils

import app.cash.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}
