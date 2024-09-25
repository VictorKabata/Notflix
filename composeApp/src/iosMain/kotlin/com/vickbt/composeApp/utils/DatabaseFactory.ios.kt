@file:OptIn(ExperimentalForeignApi::class)

package com.vickbt.composeApp.utils

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.vickbt.composeApp.data.cache.AppDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual class DatabaseFactory {
    actual fun createDatabase(): AppDatabase {
        val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )

        val dbFilePath = requireNotNull(documentDirectory?.path) + "/notflix.db"
        return Room.databaseBuilder<AppDatabase>(
            name = dbFilePath,
        ).setQueryCoroutineContext(Dispatchers.IO).fallbackToDestructiveMigration(true)
            .setDriver(BundledSQLiteDriver()).build()
    }
}
