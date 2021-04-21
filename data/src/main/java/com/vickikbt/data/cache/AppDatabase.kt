package com.vickikbt.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vickikbt.data.cache.converters.*
import com.vickikbt.data.cache.daos.MovieDetailsDao
import com.vickikbt.data.cache.daos.PopularShowsDao
import com.vickikbt.data.cache.daos.UpcomingShowsDao
import com.vickikbt.data.models.entity.*

@Database(
    entities = [PopularResultEntity::class, MovieEntity::class, UpcomingResultEntity::class, MovieDetailsEntity::class, ProductionCompanyEntity::class, ProductionCountryEntity::class, SpokenLanguageEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    MovieEntityConverter::class,
    GenreEntityConverter::class,
    ProductionCompanyEntityConverter::class,
    ProductionCountryEntityConverter::class,
    SpokenLanguageEntityConverter::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun popularShowsDao(): PopularShowsDao
    abstract fun upcomingShowsDao(): UpcomingShowsDao
    abstract fun movieDetailsDao(): MovieDetailsDao

}