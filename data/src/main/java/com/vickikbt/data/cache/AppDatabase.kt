package com.vickikbt.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vickikbt.data.cache.converters.*
import com.vickikbt.data.cache.daos.MovieDetailsDao
import com.vickikbt.data.cache.daos.PopularShowsDao
import com.vickikbt.data.cache.daos.UpcomingShowsDao
import com.vickikbt.data.models.entity.MovieDetailsEntity
import com.vickikbt.data.models.entity.MovieEntity
import com.vickikbt.data.models.entity.PopularResultEntity
import com.vickikbt.data.models.entity.UpcomingResultEntity

@Database(
    entities = [PopularResultEntity::class, MovieEntity::class, UpcomingResultEntity::class, MovieDetailsEntity::class],
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