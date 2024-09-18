package com.vickbt.composeApp.utils

import androidx.room.Room
import androidx.room.RoomDatabase
import com.vickbt.composeApp.data.cache.AppDatabase
import java.io.File

actual class DatabaseFactory {
    actual fun createDatabase(): RoomDatabase.Builder<AppDatabase> {
        val dbFile = File(System.getProperty("java.io.tmpdir"), "notflix.db")
        return Room.databaseBuilder<AppDatabase>(
            name = dbFile.absolutePath,
        )
        /*.setQueryCoroutineContext(Dispatchers.IO).fallbackToDestructiveMigration(true)
        .setDriver(BundledSQLiteDriver()).build()*/
    }

}
