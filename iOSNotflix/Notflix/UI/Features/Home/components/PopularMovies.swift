//
//  PopularMovies.swift
//  Notflix
//
//  Created by Michael Ndiritu on 19/09/2022.
//

import SwiftUI
import shared
import CachedAsyncImage

struct PopularMovies: View {
    let movies : [Movie]
    let onClick : (Int) -> Void
    var body: some View {
        VStack (alignment : .leading){
            Text("Popular Movies").bold().font(.title3)
            ScrollView(.horizontal){
                LazyHStack{
                    ForEach(movies,id: \.id ){movie in
                        
                        PopularMoviesItem(movie: movie,onClick: { id in
                            
                            onClick(id)
                        })
                         
                        
                    }
                    
                }
            }
        }
    }
}

struct PopularMovies_Previews: PreviewProvider {
    static var previews: some View {
        PopularMovies(movies:[]){ id in
        }
    }
}

struct PopularMoviesItem : View{
    let movie:Movie
    let onClick : (Int) -> Void
  @State var gradientColor : Color = .primary
    @State var textColor : Color = .primary
    
    
    var body: some View{
        let movieImage = DomainExtensions.shared.loadImage(link: movie.backdropPath!)

        ZStack(alignment : .bottomLeading){
            
            CachedAsyncImage(url: URL(string : movieImage)){image in
                image .resizable().scaledToFill().frame(maxWidth: 300,maxHeight: 216).cornerRadius(5)
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
                ProgressView()
            }
            VStack (alignment: .leading){
                Spacer()
                Text(movie.title ?? "Unknown Movie").bold().font(.headline).foregroundColor(textColor)
                HStack(spacing: 10){
                    FiveStarView(rating: Decimal(Double(truncating: movie.voteAverage ?? 0)/2), color: Color.yellow)
                        .frame(width: 60, height: 10, alignment: .leading).padding(.trailing,20)
                    Divider()
                    

                    Text(SharedExtensionsKt.getReleaseDate(movie.releaseDate) ?? " ").font(.caption).bold().foregroundColor(textColor)
                }.frame(width: 300,height: 10,alignment: .leading)            }.padding(.vertical,16).padding(.horizontal, 4)
        }.frame(maxWidth: 300,maxHeight: 218)
            .onTapGesture {
               
                onClick(Int(truncating: movie.id!))
            }
        
    }
}
