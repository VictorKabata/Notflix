//
//  PreferenceScreen.swift
//  Notflix
//
//  Created by Michael Ndiritu on 15/09/2022.


import SwiftUI
import shared

struct PreferencesScreen: View {
    @Environment(\.openURL) var openURL
    
    @StateObject var prefViewModel: PreferencesViewModel = PreferencesViewModel()
    
    
    var body: some View {
        VStack{
            Form{
                Section(header:  Text("Preferences").bold()){
                    
                    Picker("Change App Theme", selection: $prefViewModel.appTheme){
                        ForEach(AppTheme.allCases,id: \.self){theme in
                            
                            Text(theme.getName())
                                .tag(theme.rawValue)
                            
                        }
                        
                    }.onChange(of: prefViewModel.appTheme){ themeId in
                        prefViewModel.setTheme(newTheme: themeId.rawValue)
                        
                        
                    }
                    
                }
                
                Section(header: Text("Extras").bold()){
                    
                    ExtraItem(image: "bug", title: "Report Bug", description: "Report a Bug or Request a feature"){
                        lauchPRIntent(openURL: openURL )
                        
                    }
                    
                    
                    //   Link(destination: URL(string: shared.Constants.shared.SOURCE_CODE_URL)!) {
                    ExtraItem(image: "githubicon", title: "Source Code", description: "View App Source Code"){
                        openURL(URL(string: shared.Constants.shared.SOURCE_CODE_URL)!){ accepted in
                            if !accepted {
                                debugPrint("failed")
                            }
                            
                        }
                    }
                }
                
                
                
                
                // }
                Button(action: {
                    
                }, label: {
                    Text("Sign Out")
                }).frame( alignment: .bottom)
                
                
                
            }
            
            
        }.onAppear{
            prefViewModel.observeTheme()
        }
    }
    
    func lauchPRIntent(openURL: OpenURLAction) {
        let emailData = SupportEmail(toAddress: shared.Constants.shared.BUG_REPORT_EMAIL, subject: shared.Constants.shared.BUG_REPORT_SUBJECT)
        
        emailData.send(openURL: openURL)
    }


}

struct PreferencesScreen_Previews: PreviewProvider {
    static var previews: some View {
        PreferencesScreen()
    }
}


struct ExtraItem: View {
    let image:String
    let title :String
    let description: String
    let onClick : () -> Void
    
    
    var body: some View {
        HStack(alignment: .center,spacing : 16){
            Image(image)
                .renderingMode(.template)
                .resizable()
                .foregroundColor(.primary)
                .frame(width: 24, height: 24, alignment: .center)
                .padding()
            VStack(alignment: .leading){
                Text(title)
                    .bold()
                Text(description)
                    .font(.caption)
                
            }

        }.onTapGesture(perform: onClick)
    }
}


//let launchEmailIntent =
