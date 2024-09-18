package com.vickbt.composeApp.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vickbt.composeApp.data.cache.daos.FavoriteMovieDao
import com.vickbt.composeApp.data.cache.entities.MovieDetailsEntity

@Database(entities = [MovieDetailsEntity::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteMovieDao(): FavoriteMovieDao
}
