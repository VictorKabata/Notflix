//
//  PreferencesViewModel.swift
//  Notflix
//
//  Created by Michael Ndiritu on 15/09/2022.
//

import Foundation
import shared


@MainActor
class PreferencesViewModel : ObservableObject{
    @LazyKoin
    private var presenter : SharedSettingsPresenter
    
    
}
