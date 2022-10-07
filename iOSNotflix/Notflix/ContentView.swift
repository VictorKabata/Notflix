//
//  ContentView.swift
//  Notflix
//
//  Created by Michael Ndiritu on 15/09/2022.
//

import SwiftUI


struct ContentView: View {
    @State var currentDestination : Array<Int> = []
    
    @State var navigation = NavigationPath()
    @State var isLinkActive = false
    
    var body: some View {
        NavigationStack(path: $currentDestination){
         
            
            TabView{
                
                HomeScreen{ id in
                    currentDestination.append(id)
                    //print("the id of the movie is \(id)")
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
                
                
                
                
            }.accentColor(.red)
                .navigationViewStyle(.stack)
                .navigationDestination(for: Int.self){ id in
                    DetailsScreen(movieId: id)
                    
                }
            
        }.accentColor(.red)
        
        
        
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
