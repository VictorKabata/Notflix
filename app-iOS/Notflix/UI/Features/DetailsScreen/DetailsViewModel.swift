//
//  DetailsViewModel.swift
//  Notflix
//
//  Created by Michael Ndiritu on 20/09/2022.
//

import Foundation
import shared
import KMPNativeCoroutinesAsync

@MainActor
class DetailsViewModel : ObservableObject {
    @LazyKoin
  private  var presenter : SharedDetailsPresenter
    
    @Published private (set) var movieDetails: MovieDetails? = nil
    
    @Published private (set) var movieCast : Cast? = nil
    @Published private (set) var similarMovies = [Movie]()
    @Published private (set) var movieVideo : MovieVideo?
    
    func observeMovieDetails(for movieId : Int ){
        
        presenter.getMovieDetails(movieId: Int32( movieId))
        let _ = Task {
            
            do{
                let result = asyncStream(for: presenter.movieDetailsNative)
                for try await details in result{
                    movieDetails = details
                }
            }
            
        }
    }
    func observeMovieCast(for movieId : Int){
        presenter.getMovieCast(movieId: Int32(movieId))
        Task{
            do{
                let castStream  = asyncStream(for: presenter.movieCastNative)
                for try await cast in castStream{
                    movieCast = cast
                }
            }
        }
    }
    
    
    func observeSimilarMovies(for movieId: Int){
        presenter.fetchSimilarMovies(movieId: Int32(movieId))
        Task{
            let similarMovieStream  = asyncStream(for: presenter.similarMoviesNative)
            
            
            for try await  movies in similarMovieStream{
                if let movies  {
                    similarMovies = movies
                }
            }
        }
    }
    
    
    func saveMovieDetails (for movie : MovieDetails){
      
        
        presenter.saveMovieDetails(movieDetails: movie)
    }
    
    func deleteSavedMovie(for id  : Int){
        presenter.deleteFavouriteMovie(movieId: Int32(id))
    }
    
    func toggleMovieFavorites(movie : MovieDetails){
        if (movie.isFavourite == true ){
            deleteSavedMovie(for: Int(movie.id))
        } else {
            saveMovieDetails(for: movie)
        }
        
    }
    
    
    
}
