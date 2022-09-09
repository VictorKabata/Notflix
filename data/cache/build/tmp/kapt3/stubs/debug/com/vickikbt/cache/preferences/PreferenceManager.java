package com.vickikbt.cache.preferences;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u001eJ\u001a\u0010!\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u001f\u001a\u00020\u00062\b\u0010 \u001a\u0004\u0018\u00010\u0006J\u0016\u0010\"\u001a\u00020#2\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u001eJ\u0018\u0010%\u001a\u00020#2\u0006\u0010\u001f\u001a\u00020\u00062\b\u0010$\u001a\u0004\u0018\u00010\u0006R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00068BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\u0004\u0018\u00010\u00068BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\bR\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\u0004\u0018\u00010\u00068BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\bR\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u00128F\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00060\u00128F\u00a2\u0006\u0006\u001a\u0004\b\u0016\u0010\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00060\u00128F\u00a2\u0006\u0006\u001a\u0004\b\u0018\u0010\u0014R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2 = {"Lcom/vickikbt/cache/preferences/PreferenceManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_appLanguage", "", "get_appLanguage", "()Ljava/lang/String;", "_appLanguageMutableLiveData", "Landroidx/lifecycle/MutableLiveData;", "_appTheme", "get_appTheme", "_appThemeMutableLiveData", "_imageQuality", "get_imageQuality", "_imageQualityMutableLiveData", "appLanguage", "Landroidx/lifecycle/LiveData;", "getAppLanguage", "()Landroidx/lifecycle/LiveData;", "appTheme", "getAppTheme", "imageQuality", "getImageQuality", "preferenceChangedListener", "Landroid/content/SharedPreferences$OnSharedPreferenceChangeListener;", "preferences", "Landroid/content/SharedPreferences;", "getLong", "", "key", "defaultValue", "getString", "setLong", "", "value", "setString", "cache_debug"})
public final class PreferenceManager {
    private final android.content.Context context = null;
    private final android.content.SharedPreferences preferences = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _appThemeMutableLiveData = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _appLanguageMutableLiveData = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _imageQualityMutableLiveData = null;
    private final android.content.SharedPreferences.OnSharedPreferenceChangeListener preferenceChangedListener = null;
    
    public PreferenceManager(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    public final void setString(@org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.Nullable()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getString(@org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.Nullable()
    java.lang.String defaultValue) {
        return null;
    }
    
    public final void setLong(@org.jetbrains.annotations.NotNull()
    java.lang.String key, long value) {
    }
    
    public final long getLong(@org.jetbrains.annotations.NotNull()
    java.lang.String key, long defaultValue) {
        return 0L;
    }
    
    private final java.lang.String get_appTheme() {
        return null;
    }
    
    private final java.lang.String get_appLanguage() {
        return null;
    }
    
    private final java.lang.String get_imageQuality() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getAppTheme() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getAppLanguage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getImageQuality() {
        return null;
    }
}