package com.vickikbt.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vickikbt.cache.converters.*
import com.vickikbt.cache.daos.*
import com.vickikbt.cache.models.*

@Database(
    entities = [MovieEntity::class, RemoteKey::class, MovieDetailsEntity::class, CastEntity::class, MovieVideoEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    MovieEntityConverter::class,
    GenreIDEntityConverter::class,
    GenreEntityConverter::class,
    SpokenLanguageEntityConverter::class,
    ActorEntityConverter::class,
    VideoEntityConverter::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao
    abstract fun remoteKeyDao(): RemoteKeyDao
    abstract fun movieDetailsDao(): MovieDetailsDao
    abstract fun castDao(): CastDao
    abstract fun videosDao(): VideosDao
}
