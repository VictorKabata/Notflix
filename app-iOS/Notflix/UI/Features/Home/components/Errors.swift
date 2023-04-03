//
//  Errors.swift
//  Notflix
//
//  Created by Michael Ndiritu on 30/09/2022.
//

import SwiftUI

struct Errors: View {
    let message : String
    var body: some View {
        Text("hello world")
        //Alert(title: "Something Wen Wrong",message: message)
    }
}

struct Errors_Previews: PreviewProvider {
    static var previews: some View {
        Errors(message: "")
    }
}
