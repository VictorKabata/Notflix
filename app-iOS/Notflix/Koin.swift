//
//  Koin.swift
//  Notflix
//
//  Created by Michael Ndiritu on 15/09/2022.
//

//
// koin injection in swift
// https://medium.com/@uwaisalqadri/elegant-koin-injection-for-swift-in-kotlin-multiplatform-mobile-9a803f6efb2e

import shared
import Foundation

typealias KoinApplication = Koin_coreKoinApplication
typealias Koin = Koin_coreKoin

extension KoinApplication {
    static let shared = companion.start()
    
    @discardableResult
    static func start() -> KoinApplication {
        shared
    }
}

extension KoinApplication {
    private static let keyPaths: [PartialKeyPath<Koin>] = [
        \.homePresenter,
        \.detailsPresenter,
        \.favoritesPresenter,
        \.mainPresenter,
        \.settingsPresenter
         
    ]
    
    static func inject<T>() -> T {
        shared.inject()
    }
    
    func inject<T>() -> T {
        for partialKeyPath in Self.keyPaths {
            guard let keyPath = partialKeyPath as? KeyPath<Koin, T> else { continue }
            return koin[keyPath: keyPath]
        }
        
        fatalError("\(T.self) is not registered with KoinApplication")
    }
}

@propertyWrapper
struct LazyKoin<T> {
    lazy var wrappedValue: T = { KoinApplication.shared.inject() }()
    
    init() { }
}
