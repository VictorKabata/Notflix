@file:OptIn(ExperimentalForeignApi::class, ExperimentalForeignApi::class)

package com.vickbt.composeApp.utils

import androidx.room.Room
import androidx.room.RoomDatabase
import com.vickbt.composeApp.data.cache.AppDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual class DatabaseFactory {
    actual fun createDatabase(): RoomDatabase.Builder<AppDatabase> {
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
        )
    }

}
