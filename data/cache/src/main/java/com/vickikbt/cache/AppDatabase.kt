package com.vickikbt.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vickikbt.cache.converters.*
import com.vickikbt.cache.daos.*
import com.vickikbt.cache.models.*

@Database(
    entities = [PopularResultEntity::class, MovieEntity::class, UpcomingResultEntity::class, MovieDetailsEntity::class, CastEntity::class, VideoEntity::class, FavoriteMovieEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    MovieEntityConverter::class,
    GenreIDEntityConverter::class,
    GenreEntityConverter::class,
    ProductionCompanyEntityConverter::class,
    ProductionCountryEntityConverter::class,
    SpokenLanguageEntityConverter::class,
    CastItemEntityConverter::class,
    VideoItemEntityConverter::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun popularShowsDao(): PopularShowsDao
    abstract fun upcomingShowsDao(): UpcomingShowsDao
    abstract fun movieDetailsDao(): MovieDetailsDao
    abstract fun castDao(): CastDao
    abstract fun videosDao(): VideosDao
    abstract fun favoritesDao(): FavoritesDao

}