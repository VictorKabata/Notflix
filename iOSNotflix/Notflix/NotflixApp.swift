//
//  NotflixApp.swift
//  Notflix
//
//  Created by Michael Ndiritu on 15/09/2022.
//

import SwiftUI

@main
struct NotflixApp: App {
    @StateObject var viewModel : PreferencesViewModel = PreferencesViewModel()
    @Environment(\.colorScheme) var systemColorScheme: ColorScheme
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
    
    
    var colorScheme:ColorScheme{
        
        withAnimation{
        
        switch viewModel.appTheme{
        case .Dark:
            return .dark
            
        case .Light:
            return.light
        case .System:
            return systemColorScheme
            
        }
        }
    }
}
