//
//  PresentationUtilities.swift
//  Notflix
//
//  Created by Michael Ndiritu on 15/09/2022.
//

import Foundation

enum AppTheme : Int,CaseIterable{
    case Light = 0
    case Dark = 1
    
    case System = 2
    
    func getTheme(value: Int) -> AppTheme{
        switch value {
    case 1:
         return AppTheme.Dark
    case 0:
        return AppTheme.Light
    default:
        return AppTheme.System
    }
    }
    
    func getName() -> String {
        switch self{
        case .System:
            return "System Theme"
        case .Light:
            return "Light Theme"
        case .Dark:
            return " Dark Theme"
        }
    }
    
}

