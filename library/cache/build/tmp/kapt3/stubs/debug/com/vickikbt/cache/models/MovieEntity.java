package com.vickikbt.cache.models;

import java.lang.System;

@androidx.room.Entity(tableName = "Movies Table")
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b3\b\u0087\b\u0018\u00002\u00020\u0001B\u0095\u0001\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\b\u00a2\u0006\u0002\u0010\u0015J\u0010\u0010-\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0017J\u000b\u0010.\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u0010\u00100\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0017J\u0010\u00101\u001a\u0004\u0018\u00010\u000eH\u00c6\u0003\u00a2\u0006\u0002\u0010#J\u0010\u00102\u001a\u0004\u0018\u00010\bH\u00c6\u0003\u00a2\u0006\u0002\u0010+J\u000b\u00103\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u0011\u00104\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007H\u00c6\u0003J\t\u00105\u001a\u00020\bH\u00c6\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u0010\u00109\u001a\u0004\u0018\u00010\u000eH\u00c6\u0003\u00a2\u0006\u0002\u0010#J\u000b\u0010:\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u00ba\u0001\u0010;\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\b\b\u0002\u0010\t\u001a\u00020\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\bH\u00c6\u0001\u00a2\u0006\u0002\u0010<J\u0013\u0010=\u001a\u00020\u00032\b\u0010>\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010?\u001a\u00020\bH\u00d6\u0001J\t\u0010@\u001a\u00020\u0005H\u00d6\u0001R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0016\u0010\t\u001a\u00020\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0018\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001aR\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001aR\u0018\u0010\f\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001aR\u001a\u0010\r\u001a\u0004\u0018\u00010\u000e8\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010$\u001a\u0004\b\"\u0010#R\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001aR\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001aR\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u001aR\u001a\u0010\u0012\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b(\u0010\u0017R\u001a\u0010\u0013\u001a\u0004\u0018\u00010\u000e8\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010$\u001a\u0004\b)\u0010#R\u001a\u0010\u0014\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010,\u001a\u0004\b*\u0010+\u00a8\u0006A"}, d2 = {"Lcom/vickikbt/cache/models/MovieEntity;", "", "adult", "", "backdrop_path", "", "genre_ids", "", "", "id", "original_language", "original_title", "overview", "popularity", "", "poster_path", "release_date", "title", "video", "vote_average", "vote_count", "(Ljava/lang/Boolean;Ljava/lang/String;Ljava/util/List;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Double;Ljava/lang/Integer;)V", "getAdult", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getBackdrop_path", "()Ljava/lang/String;", "getGenre_ids", "()Ljava/util/List;", "getId", "()I", "getOriginal_language", "getOriginal_title", "getOverview", "getPopularity", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getPoster_path", "getRelease_date", "getTitle", "getVideo", "getVote_average", "getVote_count", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/Boolean;Ljava/lang/String;Ljava/util/List;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Double;Ljava/lang/Integer;)Lcom/vickikbt/cache/models/MovieEntity;", "equals", "other", "hashCode", "toString", "cache_debug"})
public final class MovieEntity {
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Adult")
    private final java.lang.Boolean adult = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Backdrop_Path")
    private final java.lang.String backdrop_path = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Genre_IDs")
    private final java.util.List<java.lang.Integer> genre_ids = null;
    @androidx.room.PrimaryKey(autoGenerate = false)
    @androidx.room.ColumnInfo(name = "ID")
    private final int id = 0;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Original_Language")
    private final java.lang.String original_language = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Original_Title")
    private final java.lang.String original_title = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Overview")
    private final java.lang.String overview = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Popularity")
    private final java.lang.Double popularity = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Poster_Path")
    private final java.lang.String poster_path = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Release_Date")
    private final java.lang.String release_date = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Title")
    private final java.lang.String title = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Video")
    private final java.lang.Boolean video = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Vote_Average")
    private final java.lang.Double vote_average = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Vote_Count")
    private final java.lang.Integer vote_count = null;
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean getAdult() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getBackdrop_path() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<java.lang.Integer> getGenre_ids() {
        return null;
    }
    
    public final int getId() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getOriginal_language() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getOriginal_title() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getOverview() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double getPopularity() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPoster_path() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getRelease_date() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getTitle() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean getVideo() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double getVote_average() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getVote_count() {
        return null;
    }
    
    public MovieEntity(@org.jetbrains.annotations.Nullable
    java.lang.Boolean adult, @org.jetbrains.annotations.Nullable
    java.lang.String backdrop_path, @org.jetbrains.annotations.Nullable
    java.util.List<java.lang.Integer> genre_ids, int id, @org.jetbrains.annotations.Nullable
    java.lang.String original_language, @org.jetbrains.annotations.Nullable
    java.lang.String original_title, @org.jetbrains.annotations.Nullable
    java.lang.String overview, @org.jetbrains.annotations.Nullable
    java.lang.Double popularity, @org.jetbrains.annotations.Nullable
    java.lang.String poster_path, @org.jetbrains.annotations.Nullable
    java.lang.String release_date, @org.jetbrains.annotations.Nullable
    java.lang.String title, @org.jetbrains.annotations.Nullable
    java.lang.Boolean video, @org.jetbrains.annotations.Nullable
    java.lang.Double vote_average, @org.jetbrains.annotations.Nullable
    java.lang.Integer vote_count) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<java.lang.Integer> component3() {
        return null;
    }
    
    public final int component4() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean component12() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double component13() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer component14() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.vickikbt.cache.models.MovieEntity copy(@org.jetbrains.annotations.Nullable
    java.lang.Boolean adult, @org.jetbrains.annotations.Nullable
    java.lang.String backdrop_path, @org.jetbrains.annotations.Nullable
    java.util.List<java.lang.Integer> genre_ids, int id, @org.jetbrains.annotations.Nullable
    java.lang.String original_language, @org.jetbrains.annotations.Nullable
    java.lang.String original_title, @org.jetbrains.annotations.Nullable
    java.lang.String overview, @org.jetbrains.annotations.Nullable
    java.lang.Double popularity, @org.jetbrains.annotations.Nullable
    java.lang.String poster_path, @org.jetbrains.annotations.Nullable
    java.lang.String release_date, @org.jetbrains.annotations.Nullable
    java.lang.String title, @org.jetbrains.annotations.Nullable
    java.lang.Boolean video, @org.jetbrains.annotations.Nullable
    java.lang.Double vote_average, @org.jetbrains.annotations.Nullable
    java.lang.Integer vote_count) {
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