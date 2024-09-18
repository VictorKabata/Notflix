package com.vickbt.composeApp.utils

import android.content.Context
import androidx.room.Room

object DatabaseBuilder {

    fun createDatabase(context: Context): RoomAppDatabase {
        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath("notflix.db")

        return Room.databaseBuilder<RoomAppDatabase>(
            context = appContext,
            name = dbFile.absolutePath
        ).build()
    }
}
