package com.vickikbt.cache.models;

import java.lang.System;

@androidx.room.Entity(tableName = "Movie_Details_Table")
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\bG\b\u0087\b\u0018\u00002\u00020\u0001B\u008b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\f\u001a\u00020\u0007\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\t\u0012\u000e\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\t\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u0007\u0012\u000e\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\t\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u001f\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010 \u001a\u0004\u0018\u00010\u0003\u0012\b\u0010!\u001a\u0004\u0018\u00010\u0012\u0012\b\u0010\"\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0002\u0010#J\u0010\u0010E\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010%J\u000b\u0010F\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u0010\u0010G\u001a\u0004\u0018\u00010\u0012H\u00c6\u0003\u00a2\u0006\u0002\u00106J\u000b\u0010H\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u0011\u0010I\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\tH\u00c6\u0003J\u0011\u0010J\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\tH\u00c6\u0003J\u000b\u0010K\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u0010\u0010L\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003\u00a2\u0006\u0002\u0010*J\u0010\u0010M\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003\u00a2\u0006\u0002\u0010*J\u0011\u0010N\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\tH\u00c6\u0003J\u000b\u0010O\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010P\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010Q\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010R\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u0010\u0010S\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010%J\u0010\u0010T\u001a\u0004\u0018\u00010\u0012H\u00c6\u0003\u00a2\u0006\u0002\u00106J\u0010\u0010U\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003\u00a2\u0006\u0002\u0010*J\u0010\u0010V\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003\u00a2\u0006\u0002\u0010*J\u0011\u0010W\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tH\u00c6\u0003J\u000b\u0010X\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010Y\u001a\u00020\u0007H\u00c6\u0003J\u000b\u0010Z\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010[\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010\\\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u00c4\u0002\u0010]\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\f\u001a\u00020\u00072\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\t2\u0010\b\u0002\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\t2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\t2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u0007H\u00c6\u0001\u00a2\u0006\u0002\u0010^J\u0013\u0010_\u001a\u00020\u00032\b\u0010`\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010a\u001a\u00020\u0007H\u00d6\u0001J\t\u0010b\u001a\u00020\u0005H\u00d6\u0001R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010&\u001a\u0004\b$\u0010%R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010(R\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010+\u001a\u0004\b)\u0010*R\u001e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010(R\u0016\u0010\f\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0018\u0010\r\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u0010(R\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010(R\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u0010(R\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010(R\u001a\u0010\u0011\u001a\u0004\u0018\u00010\u00128\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u00107\u001a\u0004\b5\u00106R\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u0010(R\u001e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\t8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b9\u0010-R\u001e\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\t8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b:\u0010-R\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b;\u0010(R\u001a\u0010\u0019\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010+\u001a\u0004\b<\u0010*R\u001a\u0010\u001a\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010+\u001a\u0004\b=\u0010*R\u001e\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\t8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b>\u0010-R\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b?\u0010(R\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b@\u0010(R\u0018\u0010\u001f\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\bA\u0010(R\u001a\u0010 \u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010&\u001a\u0004\bB\u0010%R\u001a\u0010!\u001a\u0004\u0018\u00010\u00128\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u00107\u001a\u0004\bC\u00106R\u001a\u0010\"\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010+\u001a\u0004\bD\u0010*\u00a8\u0006c"}, d2 = {"Lcom/vickikbt/cache/models/MovieDetailsEntity;", "", "adult", "", "backdropPath", "", "budget", "", "genresEntity", "", "Lcom/vickikbt/cache/models/GenreEntity;", "homepage", "id", "imdbId", "originalLanguage", "originalTitle", "overview", "popularity", "", "posterPath", "productionCompaniesEntity", "Lcom/vickikbt/cache/models/ProductionCompanyEntity;", "productionCountriesEntity", "Lcom/vickikbt/cache/models/ProductionCountryEntity;", "releaseDate", "revenue", "runtime", "spokenLanguagesEntity", "Lcom/vickikbt/cache/models/SpokenLanguageEntity;", "status", "tagline", "title", "video", "voteAverage", "voteCount", "(Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Double;Ljava/lang/Integer;)V", "getAdult", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getBackdropPath", "()Ljava/lang/String;", "getBudget", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getGenresEntity", "()Ljava/util/List;", "getHomepage", "getId", "()I", "getImdbId", "getOriginalLanguage", "getOriginalTitle", "getOverview", "getPopularity", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getPosterPath", "getProductionCompaniesEntity", "getProductionCountriesEntity", "getReleaseDate", "getRevenue", "getRuntime", "getSpokenLanguagesEntity", "getStatus", "getTagline", "getTitle", "getVideo", "getVoteAverage", "getVoteCount", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Double;Ljava/lang/Integer;)Lcom/vickikbt/cache/models/MovieDetailsEntity;", "equals", "other", "hashCode", "toString", "cache_debug"})
public final class MovieDetailsEntity {
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Adult")
    private final java.lang.Boolean adult = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Backdrop_Path")
    private final java.lang.String backdropPath = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Budget")
    private final java.lang.Integer budget = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Genres")
    private final java.util.List<com.vickikbt.cache.models.GenreEntity> genresEntity = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Homepage")
    private final java.lang.String homepage = null;
    @androidx.room.PrimaryKey(autoGenerate = false)
    @androidx.room.ColumnInfo(name = "ID")
    private final int id = 0;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "IMDB_ID")
    private final java.lang.String imdbId = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Original_Language")
    private final java.lang.String originalLanguage = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Original_Title")
    private final java.lang.String originalTitle = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Overview")
    private final java.lang.String overview = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Popularity")
    private final java.lang.Double popularity = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Poster_Path")
    private final java.lang.String posterPath = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Production_Companies")
    private final java.util.List<com.vickikbt.cache.models.ProductionCompanyEntity> productionCompaniesEntity = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Production_Country")
    private final java.util.List<com.vickikbt.cache.models.ProductionCountryEntity> productionCountriesEntity = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Release Date")
    private final java.lang.String releaseDate = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Revenue")
    private final java.lang.Integer revenue = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Runtime")
    private final java.lang.Integer runtime = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Spoken_Languages")
    private final java.util.List<com.vickikbt.cache.models.SpokenLanguageEntity> spokenLanguagesEntity = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Status")
    private final java.lang.String status = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Tagline")
    private final java.lang.String tagline = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Title")
    private final java.lang.String title = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Video")
    private final java.lang.Boolean video = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Vote_Average")
    private final java.lang.Double voteAverage = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Vote_Count")
    private final java.lang.Integer voteCount = null;
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean getAdult() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getBackdropPath() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getBudget() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.vickikbt.cache.models.GenreEntity> getGenresEntity() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getHomepage() {
        return null;
    }
    
    public final int getId() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getImdbId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getOriginalLanguage() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getOriginalTitle() {
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
    public final java.lang.String getPosterPath() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.vickikbt.cache.models.ProductionCompanyEntity> getProductionCompaniesEntity() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.vickikbt.cache.models.ProductionCountryEntity> getProductionCountriesEntity() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getReleaseDate() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getRevenue() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getRuntime() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.vickikbt.cache.models.SpokenLanguageEntity> getSpokenLanguagesEntity() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getTagline() {
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
    public final java.lang.Double getVoteAverage() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getVoteCount() {
        return null;
    }
    
    public MovieDetailsEntity(@org.jetbrains.annotations.Nullable
    java.lang.Boolean adult, @org.jetbrains.annotations.Nullable
    java.lang.String backdropPath, @org.jetbrains.annotations.Nullable
    java.lang.Integer budget, @org.jetbrains.annotations.Nullable
    java.util.List<com.vickikbt.cache.models.GenreEntity> genresEntity, @org.jetbrains.annotations.Nullable
    java.lang.String homepage, int id, @org.jetbrains.annotations.Nullable
    java.lang.String imdbId, @org.jetbrains.annotations.Nullable
    java.lang.String originalLanguage, @org.jetbrains.annotations.Nullable
    java.lang.String originalTitle, @org.jetbrains.annotations.Nullable
    java.lang.String overview, @org.jetbrains.annotations.Nullable
    java.lang.Double popularity, @org.jetbrains.annotations.Nullable
    java.lang.String posterPath, @org.jetbrains.annotations.Nullable
    java.util.List<com.vickikbt.cache.models.ProductionCompanyEntity> productionCompaniesEntity, @org.jetbrains.annotations.Nullable
    java.util.List<com.vickikbt.cache.models.ProductionCountryEntity> productionCountriesEntity, @org.jetbrains.annotations.Nullable
    java.lang.String releaseDate, @org.jetbrains.annotations.Nullable
    java.lang.Integer revenue, @org.jetbrains.annotations.Nullable
    java.lang.Integer runtime, @org.jetbrains.annotations.Nullable
    java.util.List<com.vickikbt.cache.models.SpokenLanguageEntity> spokenLanguagesEntity, @org.jetbrains.annotations.Nullable
    java.lang.String status, @org.jetbrains.annotations.Nullable
    java.lang.String tagline, @org.jetbrains.annotations.Nullable
    java.lang.String title, @org.jetbrains.annotations.Nullable
    java.lang.Boolean video, @org.jetbrains.annotations.Nullable
    java.lang.Double voteAverage, @org.jetbrains.annotations.Nullable
    java.lang.Integer voteCount) {
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
    public final java.lang.Integer component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.vickikbt.cache.models.GenreEntity> component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component5() {
        return null;
    }
    
    public final int component6() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component8() {
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
    public final java.lang.Double component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component12() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.vickikbt.cache.models.ProductionCompanyEntity> component13() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.vickikbt.cache.models.ProductionCountryEntity> component14() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component15() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer component16() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer component17() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.vickikbt.cache.models.SpokenLanguageEntity> component18() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component19() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component20() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component21() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean component22() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double component23() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer component24() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.vickikbt.cache.models.MovieDetailsEntity copy(@org.jetbrains.annotations.Nullable
    java.lang.Boolean adult, @org.jetbrains.annotations.Nullable
    java.lang.String backdropPath, @org.jetbrains.annotations.Nullable
    java.lang.Integer budget, @org.jetbrains.annotations.Nullable
    java.util.List<com.vickikbt.cache.models.GenreEntity> genresEntity, @org.jetbrains.annotations.Nullable
    java.lang.String homepage, int id, @org.jetbrains.annotations.Nullable
    java.lang.String imdbId, @org.jetbrains.annotations.Nullable
    java.lang.String originalLanguage, @org.jetbrains.annotations.Nullable
    java.lang.String originalTitle, @org.jetbrains.annotations.Nullable
    java.lang.String overview, @org.jetbrains.annotations.Nullable
    java.lang.Double popularity, @org.jetbrains.annotations.Nullable
    java.lang.String posterPath, @org.jetbrains.annotations.Nullable
    java.util.List<com.vickikbt.cache.models.ProductionCompanyEntity> productionCompaniesEntity, @org.jetbrains.annotations.Nullable
    java.util.List<com.vickikbt.cache.models.ProductionCountryEntity> productionCountriesEntity, @org.jetbrains.annotations.Nullable
    java.lang.String releaseDate, @org.jetbrains.annotations.Nullable
    java.lang.Integer revenue, @org.jetbrains.annotations.Nullable
    java.lang.Integer runtime, @org.jetbrains.annotations.Nullable
    java.util.List<com.vickikbt.cache.models.SpokenLanguageEntity> spokenLanguagesEntity, @org.jetbrains.annotations.Nullable
    java.lang.String status, @org.jetbrains.annotations.Nullable
    java.lang.String tagline, @org.jetbrains.annotations.Nullable
    java.lang.String title, @org.jetbrains.annotations.Nullable
    java.lang.Boolean video, @org.jetbrains.annotations.Nullable
    java.lang.Double voteAverage, @org.jetbrains.annotations.Nullable
    java.lang.Integer voteCount) {
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