package com.vickbt.composeApp.utils

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.vickbt.composeApp.data.cache.AppDatabase
import kotlinx.coroutines.Dispatchers
import java.io.File

actual class DatabaseFactory {
    actual fun createDatabase(): AppDatabase {
        val dbFile = File(System.getProperty("java.io.tmpdir"), "notflix.db")
        return Room.databaseBuilder<AppDatabase>(
            name = dbFile.absolutePath,
        ).setQueryCoroutineContext(Dispatchers.IO).fallbackToDestructiveMigration(true)
            .setDriver(BundledSQLiteDriver()).build()
    }
}
