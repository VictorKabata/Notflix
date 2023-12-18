package com.vickbt.shared.utils

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

actual class DatabaseDriverFactory(private val context:Context) {
    fun createDriver(): SqlDriver{
        // return AndroidSqliteDriver(AppDatabase.sq)
    }
}
