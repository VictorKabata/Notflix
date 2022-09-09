package com.vickikbt.cache.daos;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\f\bg\u0018\u00002\u00020\u0001J#\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u001e\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n2\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\'J\u001c\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e2\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u001e\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000b0\n2\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\'J\u0019\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013J\u0018\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\n2\u0006\u0010\u0015\u001a\u00020\u000fH\'J\u001f\u0010\u0016\u001a\u00020\u00032\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018J!\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001b\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001c"}, d2 = {"Lcom/vickikbt/cache/daos/MoviesDao;", "", "deleteMovies", "", "category", "", "isFavorite", "", "(Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFavoriteMovies", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/vickikbt/cache/models/MovieEntity;", "getMovies", "Landroidx/paging/PagingSource;", "", "Lcom/vickikbt/domain/models/Movie;", "getNowPlayingMovies", "isCategoryCacheAvailable", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isMovieFavorite", "movieId", "saveMovies", "movieEntities", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateMovieIsFavorite", "cacheId", "(IZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cache_debug"})
public abstract interface MoviesDao {
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.IGNORE)
    public abstract java.lang.Object saveMovies(@org.jetbrains.annotations.NotNull()
    java.util.List<com.vickikbt.cache.models.MovieEntity> movieEntities, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM `Movies Table` WHERE category=:category")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.vickikbt.domain.models.Movie>> getNowPlayingMovies(@org.jetbrains.annotations.NotNull()
    java.lang.String category);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM `Movies Table` WHERE category=:category")
    public abstract androidx.paging.PagingSource<java.lang.Integer, com.vickikbt.domain.models.Movie> getMovies(@org.jetbrains.annotations.NotNull()
    java.lang.String category);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "DELETE FROM `Movies Table` WHERE category=:category AND isFavorite IS NOT :isFavorite")
    public abstract java.lang.Object deleteMovies(@org.jetbrains.annotations.NotNull()
    java.lang.String category, boolean isFavorite, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "SELECT COUNT(*) FROM `Movies Table` WHERE category=:category")
    public abstract java.lang.Object isCategoryCacheAvailable(@org.jetbrains.annotations.NotNull()
    java.lang.String category, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> continuation);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM `Movies Table` WHERE isFavorite=:isFavorite")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.vickikbt.cache.models.MovieEntity>> getFavoriteMovies(boolean isFavorite);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT isFavorite FROM `Movies Table` WHERE id=:movieId AND isFavorite = 1")
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Boolean> isMovieFavorite(int movieId);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "UPDATE `Movies Table` SET isFavorite=:isFavorite WHERE cacheId=:cacheId")
    public abstract java.lang.Object updateMovieIsFavorite(int cacheId, boolean isFavorite, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 3)
    public final class DefaultImpls {
    }
}