//
//  PreferencesViewModel.swift
//  Notflix
//
//  Created by Michael Ndiritu on 15/09/2022.
//

import Foundation
import shared
import KMPNativeCoroutinesAsync


@MainActor
class PreferencesViewModel : ObservableObject{
    
    @LazyKoin
    private var presenter : SharedSettingsPresenter
    
    
    @Published var appTheme: AppTheme = AppTheme.System
    
    func observeTheme(){
        let _ = Task{
            
            do{
              
                let stream  = asyncStream(for: presenter.selectedThemeNative)
                
                for try await theme in stream {
                    
                    if let theme  = theme{
                        appTheme = appTheme.getTheme(value: Int(truncating: theme))
                    }
                    
                    
                }
            }
        }
    }
    
    func setTheme(newTheme: Int){
        
     presenter.savePreferenceSelection(key: shared.PreferenceManager.companion.THEME_KEY, selection: Int32(newTheme)) 
            
    }
    
    
    
    
    
}
