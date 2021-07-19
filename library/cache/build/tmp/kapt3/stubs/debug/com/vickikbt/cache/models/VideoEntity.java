package com.vickikbt.cache.models;

import java.lang.System;

@androidx.room.Entity(tableName = "Videos_Table")
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\u0011\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u00c6\u0003J%\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0015"}, d2 = {"Lcom/vickikbt/cache/models/VideoEntity;", "", "id", "", "videoItemsEntity", "", "Lcom/vickikbt/cache/models/VideoItemEntity;", "(ILjava/util/List;)V", "getId", "()I", "getVideoItemsEntity", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "cache_debug"})
public final class VideoEntity {
    @androidx.room.PrimaryKey(autoGenerate = false)
    @androidx.room.ColumnInfo(name = "ID")
    private final int id = 0;
    @org.jetbrains.annotations.Nullable
    @androidx.room.ColumnInfo(name = "Video Items")
    private final java.util.List<com.vickikbt.cache.models.VideoItemEntity> videoItemsEntity = null;
    
    public final int getId() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.vickikbt.cache.models.VideoItemEntity> getVideoItemsEntity() {
        return null;
    }
    
    public VideoEntity(int id, @org.jetbrains.annotations.Nullable
    java.util.List<com.vickikbt.cache.models.VideoItemEntity> videoItemsEntity) {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.vickikbt.cache.models.VideoItemEntity> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.vickikbt.cache.models.VideoEntity copy(int id, @org.jetbrains.annotations.Nullable
    java.util.List<com.vickikbt.cache.models.VideoItemEntity> videoItemsEntity) {
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