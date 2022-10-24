
//  DetailHeaderScreen.swift
//  Notflix
//
//  Created by Michael Ndiritu on 20/09/2022.
//

import SwiftUI
import shared
import CachedAsyncImage
import UIImageColors

struct DetailHeaderScreen: View {
    let details : MovieDetails?
    var isFavorite  = false
    let toggleFav : (Int,Bool)-> Void
    @State var expandedOverview: Bool = false
    @State var headerHeight : CGFloat = 320
    @State var gradientColor : Color = .primary
    @State var textColor:Color = .green
    var body: some View {
        if let details{
            
            
            let url = "https://com.vickibt.notflix/id?=\(Int32(truncating: details.id as NSNumber))"
     
            
           
            ZStack(alignment : .topLeading){
               
                ZStack{
                    let images = CachedAsyncImage(url: URL(string: details.backdropPath!.getImageLink())){ image in
                        image.resizable().scaledToFill().frame(width : UIScreen.screenWidth, height: headerHeight)                            .overlay(LinearGradient(gradient: Gradient(colors: [.clear,.clear, gradientColor.opacity(0.5),gradientColor]), startPoint: .top, endPoint: .bottom)).onAppear{
                            
                            image.asUIImage().getColors{ colors in
                                  if let colors{
                                      withAnimation{
                                          gradientColor = Color(colors.detail)
                                          textColor = Color(colors.background)
                                      }
                                  }
                                  
                                
                                  
                                  
                              }
                        }
                        
                    }placeholder: {
                        ProgressView().frame(height: headerHeight).redacted(reason: .placeholder)
                    }
                    
                    images
                    
                 
                  
                   // let _ = data.dou
                    
                   
                       
                   
                    
            
                    
                    
                }.frame(width: UIScreen.screenWidth,height: headerHeight)
                VStack(alignment : .leading){
                        Spacer()
                    Text(details.title ?? "").bold().font(.title).foregroundColor(textColor)
                        Text(SharedExtensionsKt.getMovieDuration(details.runtime ?? 0) ?? "").foregroundColor(textColor)
                }.padding(.leading,8)
             
              
            }.frame(width: UIScreen.screenWidth,height: headerHeight)  .toolbar{
                ToolbarItemGroup(placement: .navigationBarTrailing){
                    
                    
                    ShareLink(item: url,message: Text("Checkout this Movie")).background(.white.opacity(0.5)).clipShape(Circle())
                    
                }
                
                ToolbarItemGroup(placement: .navigationBarTrailing){
                    
                    let image = isFavorite ? "heart.fill" :  "heart"
                    
            
                    Button(action: {
                        toggleFav(Int(truncating:details.id as NSNumber),!isFavorite)
                    }){
Image(systemName: image).padding(4).background(.white.opacity(0.5)).clipShape(Circle())
                        
                        
                    }
                    
                }
                
            }
            
            
            
        }
    }
}

struct DetailHeaderScreen_Previews: PreviewProvider {
    static var previews: some View {
        DetailHeaderScreen(details: nil){id ,isFav in
            
        }
    }
}
