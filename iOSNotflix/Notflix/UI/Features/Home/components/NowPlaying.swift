//
//  NowPlaying.swift
//  Notflix
//
//  Created by Michael Ndiritu on 16/09/2022.
//

import SwiftUI
import shared
import CachedAsyncImage

struct NowPlaying: View {
    let nowPlayingMovies : [Movie]
    let onClick : (Int) -> Void
    var body: some View {
        
        if nowPlayingMovies.isEmpty {
            NowPlayingPlaceholder()
        } else {
            TabView{
                ForEach(nowPlayingMovies.prefix(5), id : \.id){ movie in
                  NowPlayingItem(movie: movie,onClick: onClick).frame(alignment: .top)
              
                    
                    
                }
                
                
                
            }.tabViewStyle(PageTabViewStyle())
                .indexViewStyle(.page(backgroundDisplayMode: .automatic))
                .frame( height: 300,alignment: .top)
            
        }
    }
}

struct NowPlayingPlaceholder :View{
    var body: some View{
        Rectangle().redacted(reason: .placeholder).frame(width: UIScreen.screenWidth - 30,height: 300).padding(50)
    }
}

struct NowPlaying_Previews: PreviewProvider {
    static var previews: some View {
        NowPlaying(nowPlayingMovies: []){ id in
            
        }
    }
}




struct NowPlayingItem: View{
    let movie: Movie
    let onClick : (Int) -> Void
    @State var gradientColor : Color = .primary
    @State var textColor : Color  = .primary
    
    var body: some View{
        
        let movieImage = DomainExtensions.shared.loadImage(link: movie.backdropPath!)
       
        
    
        
        ZStack(alignment: .topLeading){
            
            CachedAsyncImage(url: URL(string: movieImage)){ image in
                image
                    .resizable().scaledToFill().frame(width: UIScreen.screenWidth,height: 300)
                    .overlay(LinearGradient(gradient: Gradient(colors: [.clear,.clear, gradientColor.opacity(0.5),gradientColor]), startPoint: .top, endPoint: .bottom)).onAppear{
                        
                        image.asUIImage().getColors{ colors in
                              if let colors{
                                  withAnimation{
                                      gradientColor = Color(colors.detail)
                                      textColor = Color(colors.background)
                                  }
                              }
                              
                            
                              
                              
                          }
                    }
            } placeholder: {
                Rectangle()
            }
           
            VStack(alignment: .leading,spacing: 10){
                Spacer()
                Text(movie.title!).bold().font(.title)
                FiveStarView(rating: Decimal(Double(truncating: movie.voteAverage ?? 0)/2), color: Color.yellow)
                    .frame(width: (UIScreen.screenWidth/5), height: 20)
           
                
            }.padding(4)
              
                .frame(alignment: .leading)
                .frame(height: 300)
     
        }.onTapGesture {
            onClick(Int(movie.id!))
        }
        
    }
}
