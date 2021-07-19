package com.vickikbt.cache.models;

import java.lang.System;

@androidx.room.Entity(tableName = "Popular_Result_Table")
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B1\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\tJ\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\rJ\u0011\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\rJB\u0010\u0016\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010\u0017J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001b\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u001c\u001a\u00020\u001dH\u00d6\u0001R\u001e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u0011\u0010\r\u00a8\u0006\u001e"}, d2 = {"Lcom/vickikbt/cache/models/PopularResultEntity;", "", "page", "", "movieEntity", "", "Lcom/vickikbt/cache/models/MovieEntity;", "total_pages", "total_results", "(Ljava/lang/Integer;Ljava/util/List;ILjava/lang/Integer;)V", "getMovieEntity", "()Ljava/util/List;", "getPage", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getTotal_pages", "()I", "getTotal_results", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Integer;Ljava/util/List;ILjava/lang/Integer;)Lcom/vickikbt/cache/models/PopularResultEntity;", "equals", "", "other", "hashCode", "toString", "", "cache_debug"})
public final class PopularResultEntity {
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Page")
    private final java.lang.Integer page = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Movies")
    private final java.util.List<com.vickikbt.cache.models.MovieEntity> movieEntity = null;
    @androidx.room.PrimaryKey(autoGenerate = false)
    @androidx.room.ColumnInfo(name = "Total_Pages")
    private final int total_pages = 0;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Total_Results")
    private final java.lang.Integer total_results = null;
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getPage() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.vickikbt.cache.models.MovieEntity> getMovieEntity() {
        return null;
    }
    
    public final int getTotal_pages() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getTotal_results() {
        return null;
    }
    
    public PopularResultEntity(@org.jetbrains.annotations.Nullable
    java.lang.Integer page, @org.jetbrains.annotations.Nullable
    java.util.List<com.vickikbt.cache.models.MovieEntity> movieEntity, int total_pages, @org.jetbrains.annotations.Nullable
    java.lang.Integer total_results) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.vickikbt.cache.models.MovieEntity> component2() {
        return null;
    }
    
    public final int component3() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.vickikbt.cache.models.PopularResultEntity copy(@org.jetbrains.annotations.Nullable
    java.lang.Integer page, @org.jetbrains.annotations.Nullable
    java.util.List<com.vickikbt.cache.models.MovieEntity> movieEntity, int total_pages, @org.jetbrains.annotations.Nullable
    java.lang.Integer total_results) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String toString() {
        return null;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object p0) {
        return false;
    }
}