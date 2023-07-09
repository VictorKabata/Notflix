//
//  SimilarMovies.swift
//  Notflix
//
//  Created by Michael Ndiritu on 23/09/2022.
//

import SwiftUI
import shared
import CachedAsyncImage

struct SimilarMovies: View {
    let similarMovies : [Movie]
    var onMovieClick : (Int) -> Void
    var body: some View {
        VStack(alignment : .leading,spacing: 5){
            Text("Similar Movies").font(.title2).bold()
            ScrollView(.horizontal,showsIndicators: false){
                LazyHStack{
                    ForEach(similarMovies,id: \.id){ movie in
                        
                        SimilarMovieItem(movie: movie, onClick: onMovieClick)
                        
                    }
                }
            }
        }.padding(.horizontal,5)
    }
}

struct SimilarMovies_Previews: PreviewProvider {
    static var previews: some View {
        SimilarMovies(similarMovies: []){id in
            
        }
    }
}


struct SimilarMovieItem : View {
    let movie : Movie
    let onClick : (Int) -> Void
    var body: some View{
       
     
        VStack(alignment : .leading){
            CachedAsyncImage(url: URL(string: movie.backdropPath?.getImageLink() ?? "")){image in
                image.resizable().scaledToFill().frame(width: 150,height : 225 ).cornerRadius(5).padding(2).shadow(radius: 4)
                
            } placeholder: {
                ProgressView()
            }
            
            Text(movie.title ?? "") .lineLimit(1).multilineTextAlignment(.leading).bold().padding(.leading,4).font(.subheadline)
            FiveStarView(rating: Decimal(Double(truncating: movie.voteAverage ?? 0)/2), color: Color.yellow)
                .frame(width: 60, height: 10, alignment: .leading).padding(.trailing,20).padding(.leading,4)
            
            
        }.frame(width: 150,height: 280)
            .onTapGesture {
                onClick(Int(truncating: movie.id!))
            }
            .background{Color.clear.redacted(reason: .placeholder)}
        
        
    }
}
