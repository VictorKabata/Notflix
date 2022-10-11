//
//  MovieOverview.swift
//  Notflix
//
//  Created by Michael Ndiritu on 23/09/2022.
//

import SwiftUI

struct MovieOverview: View {
    let overView : String
    @State var showAllText = false
    var body: some View {
        VStack(alignment: .leading,spacing: 5){
          Text("Overview")
                .font(.title2)
                .bold()
            Text(overView).lineLimit(showAllText ? 900 : 3)
            
            Button(action:{withAnimation{showAllText.toggle()}} ){
//                let labelText = showAllText ? " Show Less" : "Show More"
//               Text(labelText)
                
                Text(showAllText ? " Show Less" : "Show More")
              
            }
            
        }.padding(.horizontal,5)
    }
}

struct MovieOverview_Previews: PreviewProvider {
    static var previews: some View {
        MovieOverview(overView: "jsjdkscsckjdkjsksdlsdnldncdjsdcndnvbvbdjddcnl")
    }
}
