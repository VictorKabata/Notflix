package com.vickikbt.cache.daos;

import java.lang.System;

@androidx.room.Dao
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u0011\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\'J\u0011\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\r"}, d2 = {"Lcom/vickikbt/cache/daos/PopularShowsDao;", "", "deletePopularShows", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPopularShows", "Lkotlinx/coroutines/flow/Flow;", "Lcom/vickikbt/cache/models/PopularResultEntity;", "isPopularCacheAvailable", "", "savePopularShows", "popularResultEntity", "(Lcom/vickikbt/cache/models/PopularResultEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cache_debug"})
public abstract interface PopularShowsDao {
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract java.lang.Object savePopularShows(@org.jetbrains.annotations.NotNull
    com.vickikbt.cache.models.PopularResultEntity popularResultEntity, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1);
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM Popular_Result_Table")
    public abstract kotlinx.coroutines.flow.Flow<com.vickikbt.cache.models.PopularResultEntity> getPopularShows();
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "DELETE FROM Popular_Result_Table")
    public abstract java.lang.Object deletePopularShows(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> p0);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "SELECT COUNT(*) FROM Popular_Result_Table")
    public abstract java.lang.Object isPopularCacheAvailable(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> p0);
}