//
//  HomeViewModel.swift
//  Notflix
//
//  Created by Michael Ndiritu on 16/09/2022.
//

import Foundation
import shared
import KMPNativeCoroutinesAsync

@MainActor
class HomeViewModel : ObservableObject {
    @LazyKoin
    var presenter  : SharedHomePresenter
    
    @Published var nowPlayingMovies = [Movie]()
    @Published var trendingMovies = [Movie]()
    @Published var upcomingMovies = [Movie]()
    @Published var popularMovies = [Movie]()
    @Published var errorMessages : String? = nil
    @Published var showAlert = false
    
    init(){
 
        observeMovies()
        observeTrendingMovies()
        observeUpcomingMovies()
        observePopularMovies()
    }
    
    func resetError(){
        errorMessages = nil
    }
    
    func observeErrors(){
        Task{
            do{
                let errorFlow = asyncStream(for: presenter.errorNative)
                for try await error in errorFlow {
                    
                    if let error{
                        errorMessages = error
                        showAlert = true
                    }
                
                    
                }
                
                
            }
            
        }
    }
    
    func observeMovies(){

        Task{
            do{
                let movieFlow = asyncStream(for: presenter.nowPlayingMoviesNative)
                for try await movies in movieFlow {
                    nowPlayingMovies = movies ?? []
                }
                
                
            }
            
        }
    }
    
    func observeTrendingMovies(){
        
        Task{
            do{
                let movieFlow = asyncStream(for: presenter.trendingMoviesNative)
                for try await movies in movieFlow {
                    trendingMovies = movies ?? []
                }
                
                
            }
            
        }
    }
    
    func observeUpcomingMovies(){
      
        Task{
            do{
                let movieFlow = asyncStream(for: presenter.upcomingMoviesNative)
                for try await movies in movieFlow {
                    upcomingMovies = movies ?? []
                }
                
                
            }
            
        }
    }
    func observePopularMovies(){
        Task{
            do{
                let movieFlow = asyncStream(for: presenter.popularMoviesNative)
                for try await movies in movieFlow {
                    popularMovies = movies ?? []
                }
                
                
            }
            
        }
    }
    
    func observeContent(){
        print("refreshing content")
        presenter.fetchMovies()
     
    }
    
    
}
