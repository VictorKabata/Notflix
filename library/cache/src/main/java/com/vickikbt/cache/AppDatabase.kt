package com.vickikbt.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vickikbt.cache.converters.CastItemEntityConverter
import com.vickikbt.cache.converters.GenreEntityConverter
import com.vickikbt.cache.converters.GenreIDEntityConverter
import com.vickikbt.cache.converters.MovieEntityConverter
import com.vickikbt.cache.converters.ProductionCompanyEntityConverter
import com.vickikbt.cache.converters.ProductionCountryEntityConverter
import com.vickikbt.cache.converters.SpokenLanguageEntityConverter
import com.vickikbt.cache.converters.VideoItemEntityConverter
import com.vickikbt.cache.daos.CastDao
import com.vickikbt.cache.daos.MovieDetailsDao
import com.vickikbt.cache.daos.PopularShowsDao
import com.vickikbt.cache.daos.UpcomingShowsDao
import com.vickikbt.cache.daos.VideosDao
import com.vickikbt.cache.models.CastEntity
import com.vickikbt.cache.models.MovieDetailsEntity
import com.vickikbt.cache.models.MovieEntity
import com.vickikbt.cache.models.PopularResultEntity
import com.vickikbt.cache.models.UpcomingResultEntity
import com.vickikbt.cache.models.VideoEntity

@Database(
    entities = [PopularResultEntity::class, MovieEntity::class, UpcomingResultEntity::class, MovieDetailsEntity::class, CastEntity::class, VideoEntity::class],
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
    abstract fun videsoDao(): VideosDao

}