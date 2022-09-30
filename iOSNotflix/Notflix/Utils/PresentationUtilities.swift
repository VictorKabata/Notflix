//
//  PresentationUtilities.swift
//  Notflix
//
//  Created by Michael Ndiritu on 15/09/2022.
//

import Foundation
import UIKit

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

extension UIScreen{
   static let screenWidth = UIScreen.main.bounds.size.width
   static let screenHeight = UIScreen.main.bounds.size.height
   static let screenSize = UIScreen.main.bounds.size
}



enum AppImageQuality : Int,CaseIterable{
    case High = 0
    case Medium = 1
    
    case Low = 2
    
    func getQuality(value: Int) -> AppImageQuality{
        switch value {
    case 1:
            return .Medium
    case 0:
            return .High
    default:
            return .Low
    }
    }
    
    func getName() -> String {
        switch self{
        case .High:
            return "High Quality"
        case .Medium:
            return "Medium Quality"
        case .Low:
            return "Low Quality"
        }
    }
    
}

enum AppLanguage : Int,CaseIterable{
    case English = 0
    case Spanish = 1
    
    case French = 2
    case German = 3
    
    func getQuality(value: Int) -> AppLanguage{
        switch value {
    case 0:
            return .English
    case 1:
            return .Spanish
        case 2 :
            return .French
            
        case 3 :
            return .German
    default:
            return .English
    }
    }
    
    func getName() -> String {
        switch self{
        case .English:
            return "English"
        case .Spanish:
            return "Spanish"
        case .French:
            return "French"
        case .German:
            return "German"
        }
        
    }
    
}
