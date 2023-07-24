//
//  ComposeView.swift
//  Notflix
//
//  Created by Victor Kabata on 25/07/2023.
//

import Foundation
import SwiftUI
import shared


struct ComposeView:UIViewControllerRepresentable{
    
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {}

    func makeUIViewController(context: Context) -> some UIViewController {
        MainViewControllerKt.MainViewController()
    }

}
