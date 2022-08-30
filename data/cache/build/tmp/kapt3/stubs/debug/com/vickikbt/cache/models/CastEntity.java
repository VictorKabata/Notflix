package com.vickikbt.cache.models;

import java.lang.System;

@androidx.room.Entity(tableName = "Casts Table")
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u0007J\u0011\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000bJ,\u0010\u000f\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00c6\u0001\u00a2\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0006H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001R\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0017"}, d2 = {"Lcom/vickikbt/cache/models/CastEntity;", "", "actor", "", "Lcom/vickikbt/cache/models/ActorEntity;", "id", "", "(Ljava/util/List;Ljava/lang/Integer;)V", "getActor", "()Ljava/util/List;", "getId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "copy", "(Ljava/util/List;Ljava/lang/Integer;)Lcom/vickikbt/cache/models/CastEntity;", "equals", "", "other", "hashCode", "toString", "", "cache_debug"})
public final class CastEntity {
    @org.jetbrains.annotations.Nullable()
    private final java.util.List<com.vickikbt.cache.models.ActorEntity> actor = null;
    @org.jetbrains.annotations.Nullable()
    @androidx.room.PrimaryKey(autoGenerate = false)
    private final java.lang.Integer id = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.vickikbt.cache.models.CastEntity copy(@org.jetbrains.annotations.Nullable()
    java.util.List<com.vickikbt.cache.models.ActorEntity> actor, @org.jetbrains.annotations.Nullable()
    java.lang.Integer id) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    public CastEntity(@org.jetbrains.annotations.Nullable()
    java.util.List<com.vickikbt.cache.models.ActorEntity> actor, @org.jetbrains.annotations.Nullable()
    java.lang.Integer id) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<com.vickikbt.cache.models.ActorEntity> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<com.vickikbt.cache.models.ActorEntity> getActor() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getId() {
        return null;
    }
}