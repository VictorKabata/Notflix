//
//  NowPlaying.swift
//  Notflix
//
//  Created by Michael Ndiritu on 16/09/2022.
//

import SwiftUI
import shared
import CachedAsyncImage
import Shimmer

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
                .indexViewStyle(.page(backgroundDisplayMode: .always))
                .frame( height: 350,alignment: .top)
            
        }
    }
}

struct NowPlayingPlaceholder :View{
    var body: some View{
        Rectangle().redacted(reason: .placeholder).frame(width: UIScreen.screenWidth - 30,height: 300).padding(50).shimmer()
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
    @State var showPLaceholder : Bool = true
    
    var body: some View{
        
       
        
        ZStack(alignment : .top){
        
        
        ZStack(alignment: .topLeading){
            
            CachedAsyncImage(url: URL(string: movie.backdropPath!.getImageLink())){ image in
                image
                    .resizable().scaledToFill().frame(width: UIScreen.screenWidth ,height: 300).clipped().onAppear{
                        let _ = image.asUIImage().getColors{ colors in
                            if let colors{
                                withAnimation{
                                    gradientColor = Color(colors.detail)
                                    textColor = Color(colors.background)
                                }
                            }
                            
                            
                            
                            
                        }
                        
                        withAnimation{
                            showPLaceholder = false
                        }
                    }
                
            } placeholder: {
                NowPlayingPlaceholder()
            } .overlay(LinearGradient(gradient: Gradient(colors: [.clear,.clear, gradientColor.opacity(0.5),gradientColor]), startPoint: .top, endPoint: .bottom)).onAppear{
                
                
            }
            
            VStack(alignment: .leading,spacing: 10){
                Spacer()
                Text(movie.title!).bold().font(.title).opacity(0.7).transition(.moveAndFade)
                FiveStarView(rating: Decimal(Double(truncating: movie.voteAverage ?? 0)/2), color: Color.yellow)
                    .frame(width:100, height: 10).padding(.bottom,8)
                //                FiveStarView(rating: Decimal(Double(truncating: movie.voteAverage ?? 0)/2), color: Color.yellow)
                //                    .frame(width: (UIScreen.screenWidth/5), height: 20)
                
                
            }.padding(4)
            
                .frame(alignment: .leading)
                .frame(height: 300)
            
            
        }.onTapGesture {
            if let id = movie.id{
                onClick(Int(truncating: id))
            } else {
                print("id not found")
            }
            
        }.padding(.bottom,50)
                .frame(width: UIScreen.screenWidth - 5)
        }.frame(height: 350)
            .redacted(reason: showPLaceholder ? .placeholder : [])
            
        
    }
}

extension AnyTransition {
    static var moveAndFade: AnyTransition {
        .asymmetric(
            insertion: .move(edge: .trailing).combined(with: .opacity),
            removal: .scale.combined(with: .opacity)
        )
    }
}


