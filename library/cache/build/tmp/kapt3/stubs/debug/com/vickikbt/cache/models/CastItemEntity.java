package com.vickikbt.cache.models;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0002\b\'\b\u0086\b\u0018\u00002\u00020\u0001Bq\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\u0011J\u0010\u0010%\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0013J\u0010\u0010&\u001a\u0004\u0018\u00010\u000fH\u00c6\u0003\u00a2\u0006\u0002\u0010\"J\u000b\u0010\'\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u0010\u0010(\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0016J\u000b\u0010)\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u0010\u0010+\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0016J\t\u0010,\u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u0010\u0010.\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0016J\u000b\u0010/\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u0090\u0001\u00100\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0007H\u00c6\u0001\u00a2\u0006\u0002\u00101J\u0013\u00102\u001a\u00020\u00032\b\u00103\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00104\u001a\u00020\u0005H\u00d6\u0001J\t\u00105\u001a\u00020\u0007H\u00d6\u0001R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u001a\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u001b\u0010\u0016R\u0016\u0010\n\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0019R\u001a\u0010\f\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u001f\u0010\u0016R\u0018\u0010\r\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0019R\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u000f8\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010#\u001a\u0004\b!\u0010\"R\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0019\u00a8\u00066"}, d2 = {"Lcom/vickikbt/cache/models/CastItemEntity;", "", "adult", "", "cast_id", "", "character", "", "credit_id", "gender", "id", "name", "order", "original_name", "popularity", "", "profile_path", "(Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;ILjava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/String;)V", "getAdult", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getCast_id", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getCharacter", "()Ljava/lang/String;", "getCredit_id", "getGender", "getId", "()I", "getName", "getOrder", "getOriginal_name", "getPopularity", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getProfile_path", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;ILjava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/String;)Lcom/vickikbt/cache/models/CastItemEntity;", "equals", "other", "hashCode", "toString", "cache_debug"})
public final class CastItemEntity {
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Adult")
    private final java.lang.Boolean adult = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Cast_ID")
    private final java.lang.Integer cast_id = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Character")
    private final java.lang.String character = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Credit_ID")
    private final java.lang.String credit_id = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Gender")
    private final java.lang.Integer gender = null;
    @androidx.room.ColumnInfo(name = "ID")
    private final int id = 0;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Name")
    private final java.lang.String name = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Order")
    private final java.lang.Integer order = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Original_Name")
    private final java.lang.String original_name = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Popularity")
    private final java.lang.Double popularity = null;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Profile_Path")
    private final java.lang.String profile_path = null;
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean getAdult() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getCast_id() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getCharacter() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getCredit_id() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getGender() {
        return null;
    }
    
    public final int getId() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getOrder() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getOriginal_name() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double getPopularity() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getProfile_path() {
        return null;
    }
    
    public CastItemEntity(@org.jetbrains.annotations.Nullable
    java.lang.Boolean adult, @org.jetbrains.annotations.Nullable
    java.lang.Integer cast_id, @org.jetbrains.annotations.Nullable
    java.lang.String character, @org.jetbrains.annotations.Nullable
    java.lang.String credit_id, @org.jetbrains.annotations.Nullable
    java.lang.Integer gender, int id, @org.jetbrains.annotations.Nullable
    java.lang.String name, @org.jetbrains.annotations.Nullable
    java.lang.Integer order, @org.jetbrains.annotations.Nullable
    java.lang.String original_name, @org.jetbrains.annotations.Nullable
    java.lang.Double popularity, @org.jetbrains.annotations.Nullable
    java.lang.String profile_path) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer component5() {
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
    public final java.lang.Integer component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.vickikbt.cache.models.CastItemEntity copy(@org.jetbrains.annotations.Nullable
    java.lang.Boolean adult, @org.jetbrains.annotations.Nullable
    java.lang.Integer cast_id, @org.jetbrains.annotations.Nullable
    java.lang.String character, @org.jetbrains.annotations.Nullable
    java.lang.String credit_id, @org.jetbrains.annotations.Nullable
    java.lang.Integer gender, int id, @org.jetbrains.annotations.Nullable
    java.lang.String name, @org.jetbrains.annotations.Nullable
    java.lang.Integer order, @org.jetbrains.annotations.Nullable
    java.lang.String original_name, @org.jetbrains.annotations.Nullable
    java.lang.Double popularity, @org.jetbrains.annotations.Nullable
    java.lang.String profile_path) {
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