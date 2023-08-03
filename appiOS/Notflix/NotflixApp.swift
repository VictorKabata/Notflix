//
//  NotflixApp.swift
//  Notflix
//
//  Created by Victor Kabata on 21/07/2023.
//

import SwiftUI

@main
struct NotflixApp: App {
    
    init(){
        KoinApplication.start()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
