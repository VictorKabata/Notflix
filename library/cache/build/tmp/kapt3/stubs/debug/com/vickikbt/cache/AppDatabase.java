package com.vickikbt.cache;

import java.lang.System;

@androidx.room.TypeConverters(value = {com.vickikbt.cache.converters.MovieEntityConverter.class, com.vickikbt.cache.converters.GenreIDEntityConverter.class, com.vickikbt.cache.converters.GenreEntityConverter.class, com.vickikbt.cache.converters.ProductionCompanyEntityConverter.class, com.vickikbt.cache.converters.ProductionCountryEntityConverter.class, com.vickikbt.cache.converters.SpokenLanguageEntityConverter.class, com.vickikbt.cache.converters.CastItemEntityConverter.class, com.vickikbt.cache.converters.VideoItemEntityConverter.class})
@androidx.room.Database(entities = {com.vickikbt.cache.models.PopularResultEntity.class, com.vickikbt.cache.models.MovieEntity.class, com.vickikbt.cache.models.UpcomingResultEntity.class, com.vickikbt.cache.models.MovieDetailsEntity.class, com.vickikbt.cache.models.CastEntity.class, com.vickikbt.cache.models.VideoEntity.class, com.vickikbt.cache.models.FavoriteMovieEntity.class}, version = 1, exportSchema = false)
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u000eH&\u00a8\u0006\u000f"}, d2 = {"Lcom/vickikbt/cache/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "castDao", "Lcom/vickikbt/cache/daos/CastDao;", "favoritesDao", "Lcom/vickikbt/cache/daos/FavoritesDao;", "movieDetailsDao", "Lcom/vickikbt/cache/daos/MovieDetailsDao;", "popularShowsDao", "Lcom/vickikbt/cache/daos/PopularShowsDao;", "upcomingShowsDao", "Lcom/vickikbt/cache/daos/UpcomingShowsDao;", "videosDao", "Lcom/vickikbt/cache/daos/VideosDao;", "cache_debug"})
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    
    @org.jetbrains.annotations.NotNull
    public abstract com.vickikbt.cache.daos.PopularShowsDao popularShowsDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.vickikbt.cache.daos.UpcomingShowsDao upcomingShowsDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.vickikbt.cache.daos.MovieDetailsDao movieDetailsDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.vickikbt.cache.daos.CastDao castDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.vickikbt.cache.daos.VideosDao videosDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.vickikbt.cache.daos.FavoritesDao favoritesDao();
    
    public AppDatabase() {
        super();
    }
}