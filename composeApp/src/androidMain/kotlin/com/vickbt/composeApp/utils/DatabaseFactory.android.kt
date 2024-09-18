package com.vickbt.composeApp.utils

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vickbt.composeApp.data.cache.AppDatabase

actual class DatabaseFactory(private val context: Context) {
    actual fun createDatabase(): RoomDatabase.Builder<AppDatabase> {
        val appContext = context.applicationContext

        val dbFile = appContext.getDatabasePath("notflix.db")
        return Room.databaseBuilder<AppDatabase>(
            context = appContext,
            name = dbFile.absolutePath
        )
    }

}
