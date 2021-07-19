package com.company.home.ui.fragments;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u000eH\u0002J\u0010\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J$\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0012\u0010\u001b\u001a\u00020\u000e2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u000eH\u0016J\u0010\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n\u00a8\u0006 "}, d2 = {"Lcom/company/home/ui/fragments/HomeFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/vickikbt/notflix/util/StateListener;", "Lcom/vickikbt/notflix/util/OnClick;", "()V", "binding", "Lcom/company/home/databinding/FragmentHomeBinding;", "viewModel", "Lcom/company/home/ui/fragments/HomeViewModel;", "getViewModel", "()Lcom/company/home/ui/fragments/HomeViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "initUI", "", "injectFeatures", "onClick", "movieId", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onError", "message", "", "onLoading", "onSuccess", "home_debug"})
public final class HomeFragment extends androidx.fragment.app.Fragment implements com.vickikbt.notflix.util.StateListener, com.vickikbt.notflix.util.OnClick {
    private com.company.home.databinding.FragmentHomeBinding binding;
    private final kotlin.Lazy viewModel$delegate = null;
    
    private final com.company.home.ui.fragments.HomeViewModel getViewModel() {
        return null;
    }
    
    private final void injectFeatures() {
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    private final void initUI() {
    }
    
    @java.lang.Override
    public void onClick(int movieId) {
    }
    
    @java.lang.Override
    public void onLoading() {
    }
    
    @java.lang.Override
    public void onSuccess(@org.jetbrains.annotations.NotNull
    java.lang.String message) {
    }
    
    @java.lang.Override
    public void onError(@org.jetbrains.annotations.Nullable
    java.lang.String message) {
    }
    
    public HomeFragment() {
        super();
    }
}