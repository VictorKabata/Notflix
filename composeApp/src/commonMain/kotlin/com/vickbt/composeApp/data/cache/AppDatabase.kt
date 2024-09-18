package com.vickbt.composeApp.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase()
