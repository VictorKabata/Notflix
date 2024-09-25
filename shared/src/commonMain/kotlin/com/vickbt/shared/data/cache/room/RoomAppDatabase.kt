package com.vickbt.shared.data.cache.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.vickbt.shared.data.cache.room.daos.FavoriteMovieDao
import com.vickbt.shared.data.cache.room.entities.FavoriteMovieEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(entities = [FavoriteMovieEntity::class], version = 1)
abstract class RoomAppDatabase : RoomDatabase() {
    abstract fun favoriteMovieDao(): FavoriteMovieDao
}
