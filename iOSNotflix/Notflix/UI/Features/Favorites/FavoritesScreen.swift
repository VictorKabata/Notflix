//
//  FavoritesScreen.swift
//  Notflix
//
//  Created by Michael Ndiritu on 24/10/2022.
//

import SwiftUI
import shared
import CachedAsyncImage


struct FavoritesScreen : View{
    @StateObject var viewModel : FavoritesViewModel = FavoritesViewModel()
    let onClick : (Int) -> Void
    
    var body : some View{
        let columns = [
            GridItem(.flexible()),
            GridItem(.flexible()),
        ]
        
        ZStack{
        if (viewModel.favoritesMovies.isEmpty){
            Text("There are currently no favorite Movies")
        } else {
            ScrollView(.vertical){
                LazyVGrid(columns: columns){
                    ForEach(viewModel.favoritesMovies,id: \.id){ movie in
                        
                        FavoriteMovieItem(movie: movie,onClick: onClick)
                    }
                }
            }
        }
        }.navigationTitle("Favorites")
    }
}





struct FavoriteMovieItem : View {
    let movie : MovieDetails
    let onClick : (Int) -> Void
    var body: some View{
       
     
        VStack(alignment : .leading){
            CachedAsyncImage(url: URL(string: movie.backdropPath?.getImageLink() ?? "")){image in
                image.resizable().scaledToFill().frame(width: UIScreen.screenWidth/2 - 10,height : 225 ).cornerRadius(5).padding(2).shadow(radius: 4)
                
            } placeholder: {
                ProgressView()
            }
            
            Text(movie.title ?? "") .lineLimit(1).multilineTextAlignment(.leading).bold().padding(.leading,4).font(.subheadline)
            FiveStarView(rating: Decimal(Double(truncating: movie.voteAverage ?? 0)/2), color: Color.yellow)
                .frame(width: 60, height: 10, alignment: .leading).padding(.trailing,20).padding(.leading,4)
            
            
        }.frame(width: UIScreen.screenWidth/2 - 10,height: 280)
            .onTapGesture {
                onClick(Int(truncating: movie.id as NSNumber))
            }
            .background{Color.clear.redacted(reason: .placeholder)}
        
        
    }
}
