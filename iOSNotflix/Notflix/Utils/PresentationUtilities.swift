//
//  PresentationUtilities.swift
//  Notflix
//
//  Created by Michael Ndiritu on 15/09/2022.
//

import Foundation

enum AppTheme : Int,CaseIterable{
    case Dark = 1
    case Light = 2
    case System = 3
    
    func getTheme(value: Int) -> AppTheme{
        switch value {
    case 1:
         return AppTheme.Dark
    case 2:
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

