package com.company.home.ui.adapters;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\rH\u0016J\u0018\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0017J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/company/home/ui/adapters/HomeViewPagerAdapter;", "Landroidx/viewpager/widget/PagerAdapter;", "context", "Landroid/content/Context;", "showsList", "", "Lcom/vickikbt/repository/models/Movie;", "(Landroid/content/Context;Ljava/util/List;)V", "destroyItem", "", "container", "Landroid/view/ViewGroup;", "position", "", "object", "", "getCount", "instantiateItem", "isViewFromObject", "", "view", "Landroid/view/View;", "home_debug"})
public final class HomeViewPagerAdapter extends androidx.viewpager.widget.PagerAdapter {
    private final android.content.Context context = null;
    private final java.util.List<com.vickikbt.repository.models.Movie> showsList = null;
    
    @java.lang.Override
    public int getCount() {
        return 0;
    }
    
    @java.lang.Override
    public boolean isViewFromObject(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.NotNull
    java.lang.Object object) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    @java.lang.Override
    public java.lang.Object instantiateItem(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup container, int position) {
        return null;
    }
    
    @java.lang.Override
    public void destroyItem(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup container, int position, @org.jetbrains.annotations.NotNull
    java.lang.Object object) {
    }
    
    public HomeViewPagerAdapter(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.util.List<com.vickikbt.repository.models.Movie> showsList) {
        super();
    }
}