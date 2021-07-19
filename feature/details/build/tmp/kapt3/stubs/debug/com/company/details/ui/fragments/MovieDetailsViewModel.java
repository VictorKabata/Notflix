package com.company.details.ui.fragments;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0002J\u000e\u0010\u0013\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!J\u0010\u0010\"\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0002J\u0010\u0010\u0015\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\r0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0011\u00a8\u0006#"}, d2 = {"Lcom/company/details/ui/fragments/MovieDetailsViewModel;", "Landroidx/lifecycle/ViewModel;", "movieDetailsRepository", "Lcom/vickikbt/repository/repositories/movie_details/MovieDetailsRepository;", "(Lcom/vickikbt/repository/repositories/movie_details/MovieDetailsRepository;)V", "_castsMutableLiveData", "Landroidx/lifecycle/MutableLiveData;", "Lcom/vickikbt/repository/models/Cast;", "_movieDetailsMutableLiveData", "Lcom/vickikbt/repository/models/MovieDetails;", "_similarMoviesMutableLiveData", "Lcom/vickikbt/repository/models/SimilarResult;", "_videosMutableLiveData", "Lcom/vickikbt/repository/models/Video;", "cast", "Landroidx/lifecycle/LiveData;", "getCast", "()Landroidx/lifecycle/LiveData;", "movieDetails", "getMovieDetails", "similarMovies", "getSimilarMovies", "stateListener", "Lcom/vickikbt/notflix/util/StateListener;", "getStateListener", "()Lcom/vickikbt/notflix/util/StateListener;", "setStateListener", "(Lcom/vickikbt/notflix/util/StateListener;)V", "video", "getVideo", "getMovieCast", "", "movieId", "", "getMovieVideos", "details_debug"})
public final class MovieDetailsViewModel extends androidx.lifecycle.ViewModel {
    private final androidx.lifecycle.MutableLiveData<com.vickikbt.repository.models.MovieDetails> _movieDetailsMutableLiveData = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<com.vickikbt.repository.models.MovieDetails> movieDetails = null;
    private final androidx.lifecycle.MutableLiveData<com.vickikbt.repository.models.Cast> _castsMutableLiveData = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<com.vickikbt.repository.models.Cast> cast = null;
    private final androidx.lifecycle.MutableLiveData<com.vickikbt.repository.models.Video> _videosMutableLiveData = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<com.vickikbt.repository.models.Video> video = null;
    private final androidx.lifecycle.MutableLiveData<com.vickikbt.repository.models.SimilarResult> _similarMoviesMutableLiveData = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<com.vickikbt.repository.models.SimilarResult> similarMovies = null;
    @org.jetbrains.annotations.Nullable
    private com.vickikbt.notflix.util.StateListener stateListener;
    private final com.vickikbt.repository.repositories.movie_details.MovieDetailsRepository movieDetailsRepository = null;
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.vickikbt.repository.models.MovieDetails> getMovieDetails() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.vickikbt.repository.models.Cast> getCast() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.vickikbt.repository.models.Video> getVideo() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.vickikbt.repository.models.SimilarResult> getSimilarMovies() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.vickikbt.notflix.util.StateListener getStateListener() {
        return null;
    }
    
    public final void setStateListener(@org.jetbrains.annotations.Nullable
    com.vickikbt.notflix.util.StateListener p0) {
    }
    
    public final void getMovieDetails(int movieId) {
    }
    
    private final void getMovieCast(int movieId) {
    }
    
    private final void getMovieVideos(int movieId) {
    }
    
    private final void getSimilarMovies(int movieId) {
    }
    
    public MovieDetailsViewModel(@org.jetbrains.annotations.NotNull
    com.vickikbt.repository.repositories.movie_details.MovieDetailsRepository movieDetailsRepository) {
        super();
    }
}