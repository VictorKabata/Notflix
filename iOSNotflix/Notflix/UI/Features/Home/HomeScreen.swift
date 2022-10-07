//
//  HomeScreen.swift
//  Notflix
//
//  Created by Michael Ndiritu on 16/09/2022.
//

import SwiftUI

struct HomeScreen: View {
    
    @StateObject var viewModel = HomeViewModel()
    let navigatetoDetails : (Int) -> Void
    var body: some View {
        ScrollView {
            LazyVStack(spacing: 10){
                
            
                
               
                NowPlaying(nowPlayingMovies: viewModel.nowPlayingMovies,onClick: navigatetoDetails)
                 
            
                TrendingMovies(movies: viewModel.trendingMovies,onClick: navigatetoDetails).padding(.leading,10).padding(.top,10)
                
                PopularMovies(movies: viewModel.popularMovies,onClick: navigatetoDetails).padding(.leading,10).padding(.top,10)
                
                TrendingMovies(header: "Upcoming Movies" ,movies: viewModel.upcomingMovies,onClick: navigatetoDetails).padding(.leading,10).padding(.top,10)
            
                    
                
            }
        }
            .refreshable {
                Task{
                    viewModel.observeContent()
                }
            }.alert("Something Went Wrong", isPresented: $viewModel.showAlert) {
                Button("OK", role: .cancel) {
                    viewModel.resetError()
                    viewModel.observeContent()
                }
            }
    }
}

struct HomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        HomeScreen(){ id in
            
        }
    }
}
