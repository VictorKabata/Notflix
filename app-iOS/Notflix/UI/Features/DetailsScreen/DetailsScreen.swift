//
//  DetailsScreen.swift
//  Notflix
//
//  Created by Michael Ndiritu on 20/09/2022.
//

import SwiftUI

struct DetailsScreen: View {
  var movieId : Int
    var similarMovie : (Int)-> Void
    @StateObject var viewModel : DetailsViewModel = DetailsViewModel()
   // an environment variable to programmatically dismiss a screen
    @Environment(\.dismiss) var dismiss
    var body: some View {
        ScrollView{
            
            VStack(spacing : 20){
                DetailHeaderScreen(details: viewModel.movieDetails,isFavorite: false){ movie in
                  
                    
                    viewModel.toggleMovieFavorites(movie: movie)
                    
                    if (movie.isFavourite == true){ dismiss()}  else {
                        viewModel.observeMovieDetails(for: Int(movie.id))
                    }
                    
                }
                RatingView(details: viewModel.movieDetails)
                MovieOverview(overView: viewModel.movieDetails?.overview ?? "")
                MovieCast(actors: viewModel.movieCast?.actor ?? [])
                SimilarMovies(similarMovies: viewModel.similarMovies,onMovieClick: similarMovie)
                
            }
        }.onAppear{
            getMovieDetails(id: movieId)
           
        }
    }
    
 func getMovieDetails(id : Int){
       
        viewModel.observeMovieCast(for: id)
        viewModel.observeMovieDetails(for: id)
        viewModel.observeSimilarMovies(for: id)
    }
}

struct DetailsScreen_Previews: PreviewProvider {
    static var previews: some View {
        DetailsScreen(movieId: 5){
            id in
        }
    }
}
