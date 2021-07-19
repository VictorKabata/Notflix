package com.company.details.ui.fragments;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0014H\u0002J\b\u0010\u0016\u001a\u00020\u0014H\u0003J\u0010\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u0014H\u0002J\u0010\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J$\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\u0012\u0010&\u001a\u00020\u00142\b\u0010\'\u001a\u0004\u0018\u00010(H\u0016J\b\u0010)\u001a\u00020\u0014H\u0016J\u0010\u0010*\u001a\u00020\u00142\u0006\u0010\'\u001a\u00020(H\u0016R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006+"}, d2 = {"Lcom/company/details/ui/fragments/MovieDetailsFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/vickikbt/notflix/util/StateListener;", "Lcom/vickikbt/notflix/util/OnClick;", "()V", "args", "Lcom/company/details/ui/fragments/MovieDetailsFragmentArgs;", "getArgs", "()Lcom/company/details/ui/fragments/MovieDetailsFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "binding", "Lcom/company/details/databinding/FragmentMovieDetailsBinding;", "viewModel", "Lcom/company/details/ui/fragments/MovieDetailsViewModel;", "getViewModel", "()Lcom/company/details/ui/fragments/MovieDetailsViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "initCastRecyclerview", "", "initSimilarMoviesRecyclerview", "initUI", "initVideoPlayer", "movieDetails", "Lcom/vickikbt/repository/models/MovieDetails;", "injectFeatures", "onClick", "movieId", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onError", "message", "", "onLoading", "onSuccess", "details_debug"})
public final class MovieDetailsFragment extends androidx.fragment.app.Fragment implements com.vickikbt.notflix.util.StateListener, com.vickikbt.notflix.util.OnClick {
    private com.company.details.databinding.FragmentMovieDetailsBinding binding;
    private final kotlin.Lazy viewModel$delegate = null;
    private final androidx.navigation.NavArgsLazy args$delegate = null;
    
    private final com.company.details.ui.fragments.MovieDetailsViewModel getViewModel() {
        return null;
    }
    
    private final void injectFeatures() {
    }
    
    private final com.company.details.ui.fragments.MovieDetailsFragmentArgs getArgs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    private final void initUI() {
    }
    
    private final void initCastRecyclerview() {
    }
    
    private final void initVideoPlayer(com.vickikbt.repository.models.MovieDetails movieDetails) {
    }
    
    private final void initSimilarMoviesRecyclerview() {
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
    
    public MovieDetailsFragment() {
        super();
    }
}