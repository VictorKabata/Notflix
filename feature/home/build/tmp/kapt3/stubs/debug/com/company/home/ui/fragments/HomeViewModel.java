package com.company.home.ui.fragments;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u0019H\u0002R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000b0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/company/home/ui/fragments/HomeViewModel;", "Landroidx/lifecycle/ViewModel;", "popularMoviesRepository", "Lcom/vickikbt/repository/repositories/popular_movies/PopularMoviesRepository;", "upcomingMoviesRepository", "Lcom/vickikbt/repository/repositories/upcoming_movies/UpcomingRepository;", "(Lcom/vickikbt/repository/repositories/popular_movies/PopularMoviesRepository;Lcom/vickikbt/repository/repositories/upcoming_movies/UpcomingRepository;)V", "_popularMoviesMutableLiveData", "Landroidx/lifecycle/MutableLiveData;", "Lcom/vickikbt/domain/models/PopularResult;", "_upcomingMoviesMutableLiveData", "Lcom/vickikbt/domain/models/UpcomingResult;", "popularMovies", "Landroidx/lifecycle/LiveData;", "getPopularMovies", "()Landroidx/lifecycle/LiveData;", "stateListener", "Lcom/vickikbt/notflix/util/StateListener;", "getStateListener", "()Lcom/vickikbt/notflix/util/StateListener;", "setStateListener", "(Lcom/vickikbt/notflix/util/StateListener;)V", "upcomingMovies", "getUpcomingMovies", "fetchPopularMovies", "", "fetchUpcomingMovies", "home_debug"})
public final class HomeViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.Nullable
    private com.vickikbt.notflix.util.StateListener stateListener;
    private final androidx.lifecycle.MutableLiveData<com.vickikbt.domain.models.PopularResult> _popularMoviesMutableLiveData = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<com.vickikbt.domain.models.PopularResult> popularMovies = null;
    private final androidx.lifecycle.MutableLiveData<com.vickikbt.domain.models.UpcomingResult> _upcomingMoviesMutableLiveData = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<com.vickikbt.domain.models.UpcomingResult> upcomingMovies = null;
    private final com.vickikbt.repository.repositories.popular_movies.PopularMoviesRepository popularMoviesRepository = null;
    private final com.vickikbt.repository.repositories.upcoming_movies.UpcomingRepository upcomingMoviesRepository = null;
    
    @org.jetbrains.annotations.Nullable
    public final com.vickikbt.notflix.util.StateListener getStateListener() {
        return null;
    }
    
    public final void setStateListener(@org.jetbrains.annotations.Nullable
    com.vickikbt.notflix.util.StateListener p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.vickikbt.domain.models.PopularResult> getPopularMovies() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.vickikbt.domain.models.UpcomingResult> getUpcomingMovies() {
        return null;
    }
    
    private final void fetchPopularMovies() {
    }
    
    private final void fetchUpcomingMovies() {
    }
    
    public HomeViewModel(@org.jetbrains.annotations.NotNull
    com.vickikbt.repository.repositories.popular_movies.PopularMoviesRepository popularMoviesRepository, @org.jetbrains.annotations.NotNull
    com.vickikbt.repository.repositories.upcoming_movies.UpcomingRepository upcomingMoviesRepository) {
        super();
    }
}