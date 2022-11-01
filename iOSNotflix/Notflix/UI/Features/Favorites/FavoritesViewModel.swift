//
//  FavoritesViewModel.swift
//  Notflix
//
//  Created by Michael Ndiritu on 24/10/2022.
//

import Foundation
import shared
import KMPNativeCoroutinesAsync


@MainActor
class FavoritesViewModel : ObservableObject {
    
    @LazyKoin
    private var presenter : SharedFavouritesPresenter
    
    @Published var favoritesMovies = [MovieDetails]()
    
    
    
    init(){
        observeFavorites()
    }
    
    
    private func observeFavorites(){
        Task{
            let movieStream = asyncStream(for : presenter.favouriteMoviesNative)
            for try await movies in movieStream {
             
                favoritesMovies = movies ?? []
            }
            
        }
    }
}
