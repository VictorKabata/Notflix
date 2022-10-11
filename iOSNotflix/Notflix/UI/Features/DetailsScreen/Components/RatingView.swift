//
//  MovieOverView]].swift
//  Notflix
//
//  Created by Michael Ndiritu on 23/09/2022.
//

import SwiftUI
import shared

struct RatingView: View {
    let details : MovieDetails?
    
    var body: some View {
        HStack{
            Spacer()
            PopularityItem(popularity: SharedExtensionsKt.getPopularity(Double(truncating: details?.voteAverage  ?? 0.0)))
            Spacer()
            RatingItem(rating: SharedExtensionsKt.getRating(Double(truncating: details?.voteAverage ?? 0.0)))
            Spacer()
            
            
        }
    }
    
    struct MovieOverView___Previews: PreviewProvider {
        static var previews: some View {
            Text("hello ")
            //MovieOverview()
        }
    }
    
    
    struct PopularityItem : View {
        let popularity: String?
        var body: some View{
            VStack(spacing :10){
                Text(popularity ?? "N/A").bold().font(.largeTitle)
                Text("Popularity").bold().font(.title2)
            }
            
        }
    }
    
    
    struct RatingItem  : View {
        let rating : String?
        var body: some View{
            
            VStack(spacing: 10){
                
                Image(systemName: "star.fill")
                    .resizable()
                    .frame(width: 40, height: 40)
                    .foregroundColor(Color.yellow)
                
                
                Text(movieRating).bold().font(.title2)
            }
        }
        var movieRating : String {
            if rating == nil {
                return "N/A"
            } else {
                return "\(rating!)/5.0"
            }
        }
    }
}
