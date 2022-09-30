//
//  DetailsScreen.swift
//  Notflix
//
//  Created by Michael Ndiritu on 20/09/2022.
//

import SwiftUI

struct DetailsScreen: View {
  var movieId : Int
    @StateObject var viewModel : DetailsViewModel = DetailsViewModel()
    var body: some View {
        ScrollView{
            VStack(spacing : 20){
                DetailHeaderScreen(details: viewModel.movieDetails,isFavorite: false){id, isFav in
                    viewModel.toggleFavorites(movieId : id,isFav : isFav)
                }.ignoresSafeArea()
                RatingView(details: viewModel.movieDetails)
                MovieOverview(overView: viewModel.movieDetails?.overview ?? "")
                MovieCast(actors: viewModel.movieCast?.actor ?? [])
                SimilarMovies(similarMovies: viewModel.similarMovies){ id in
                    
                    getMovieDetails(id: id)
                    
                }
                
            }
        }.onAppear{
            getMovieDetails(id: movieId)
           
        }.ignoresSafeArea(.container,edges: .top)
    }
    
 func getMovieDetails(id : Int){
       
        viewModel.observeMovieCast(for: id)
        viewModel.observeMovieDetails(for: id)
        viewModel.observeSimilarMovies(for: id)
    }
}

struct DetailsScreen_Previews: PreviewProvider {
    static var previews: some View {
        DetailsScreen(movieId: 5)
    }
}
