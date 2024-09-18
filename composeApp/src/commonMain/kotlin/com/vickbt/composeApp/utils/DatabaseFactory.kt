package com.vickbt.composeApp.utils

import androidx.room.RoomDatabase
import com.vickbt.composeApp.data.cache.AppDatabase

expect class DatabaseFactory {

    fun createDatabase(): RoomDatabase.Builder<AppDatabase>

}
