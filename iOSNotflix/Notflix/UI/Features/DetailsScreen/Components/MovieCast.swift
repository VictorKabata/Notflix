//
//  MovieCast.swift
//  Notflix
//
//  Created by Michael Ndiritu on 23/09/2022.
//

import SwiftUI
import shared
import CachedAsyncImage

struct MovieCast: View {
    let actors : [Actor]
    var body: some View {
        VStack(alignment : .leading,spacing: 5){
            Text("Cast").bold().font(.title2)
            ScrollView(.horizontal){
                LazyHStack{
                    ForEach(actors,id: \.id){ actor in
                        
                        ActorItem(actor: actor)
                        
                    }
                }
            }
        }.padding(.horizontal,5)
    }
}

struct MovieCast_Previews: PreviewProvider {
    static var previews: some View {
        MovieCast(actors: [])
    }
}

struct ActorItem : View {
    let actor :  Actor
    var body: some View{
        VStack{
            
            let image = DomainExtensions.shared.loadImage(link: actor.profilePath ?? " ")
            
            CachedAsyncImage(url: URL(string: image)){image in
                image.resizable().scaledToFill().frame(width :  80 ,height: 80).clipShape(Circle())
                
                
            } placeholder: {
                ProgressView()
            }
            
            Text(actor.name ?? "Unknown Actor").font(.caption).frame(width:  80).bold().lineLimit(1)
            Text(actor.character ??  "Unknown Character ").frame(width: 76).font(.caption2).foregroundColor(.gray).lineLimit(1)
            
        }.frame(width: 80,height: 120)
    }
}
