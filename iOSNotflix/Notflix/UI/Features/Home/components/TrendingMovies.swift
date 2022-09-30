//
//  TrendingMovies.swift
//  Notflix
//
//  Created by Michael Ndiritu on 19/09/2022.
//

import SwiftUI
import shared
import CachedAsyncImage

struct TrendingMovies: View {
    var header : String  = "Trending Movies"
    let movies : [Movie]
    let onClick : (Int) -> Void
    var body: some View {
        
        VStack (alignment: .leading){
            Text(header).bold().font(.title3)
            ScrollView(.horizontal){
                LazyHStack{
                    ForEach(movies,id: \.id ){ movie in
                        TrendingMovieItem(movie: movie,onClick: onClick)
                        
                    }
                    
                }
                
            }
        }
    }
}

struct TrendingMovies_Previews: PreviewProvider {
    static var previews: some View {
        TrendingMovies(movies:[]){ id in
            
        }
    }
}





struct TrendingMovieItem : View {
    let movie : Movie
    let onClick : (Int) -> Void
    var body: some View{
        let movieImage = DomainExtensions.shared.loadImage(link: movie.posterPath!)
        let _ = print("the image link is \(movieImage)")
        VStack{
            CachedAsyncImage(url: URL(string: movieImage )){image in
                image.resizable().frame(width: 150,height : 225 ).aspectRatio(contentMode: .fill).cornerRadius(5).padding(2).shadow(radius: 4)
                
            } placeholder: {
                ProgressView()
            }
            
            Text(movie.title ?? "") .lineLimit(1).multilineTextAlignment(.leading)
            
            
        }.frame(width: 150,height : 275 )
            .onTapGesture {
                onClick(Int(movie.id!))
            }
        
        
    }
}
