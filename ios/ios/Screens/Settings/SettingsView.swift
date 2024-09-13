//
//  SettingsView.swift
//  ios
//
//  Created by Mateusz Zaremba on 13/09/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SettingsView: View {
    @StateViewModel var viewModel = SettingsViewModel()
    
    var body: some View {
        NavigationView {
            VStack {  
                LoadingScreen()
            }
            .navigationBarTitle("Settings", displayMode: .inline)
            .background(AppColors.background.edgesIgnoringSafeArea(.all))
            .onAppear {
                viewModel.onStart()
            }
            .onDisappear {
                viewModel.onClear()
            }
        }
    }
}

struct LoadingScreen: View {
    var body: some View {
        HStack {
            ProgressView().progressViewStyle(.circular)
        }.background(AppColors.background.edgesIgnoringSafeArea(.all))
    }
}
