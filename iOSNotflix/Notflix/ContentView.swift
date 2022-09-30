//
//  ContentView.swift
//  Notflix
//
//  Created by Michael Ndiritu on 15/09/2022.
//

import SwiftUI


struct ContentView: View {
    @State var currentDestination : [Int] = []
    
    @State var navigation = NavigationPath()
    @State var isLinkActive = false
    
    var body: some View {
        NavigationStack(path: $currentDestination ){
            
            TabView{
                //NavigationLink(value: <#T##Hashable?#>, label: <#T##() -> View#>)
                HomeScreen{ id in
                    
                    //print("the id of the movie is \(id)")
                   // currentDestination.removeAll()
                    currentDestination.append(id)
                    
                    
                }.tabItem{
                    Image(systemName: "house")
                    
                }
                
                Text("Hello Dev")
                    .tabItem{
                        Image(systemName: "heart")
                    }
                PreferencesScreen().tabItem{
                    Image(systemName: "gear")
                }
                
            }.navigationViewStyle(.stack)
                .navigationDestination(for: Int.self){ id in
                    DetailsScreen(movieId: id)
                    
                }
            
            
        }
    }
    
    
 
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
