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
            Text(overView).lineLimit(showAllText ? 900 : 3).onTapGesture {withAnimation{showAllText.toggle()}}

            
        }.padding(.horizontal,5)
    }
}

struct MovieOverview_Previews: PreviewProvider {
    static var previews: some View {
        MovieOverview(overView: "jsjdkscsckjdkjsksdlsdnldncdjsdcndnvbvbdjddcnl")
    }
}
