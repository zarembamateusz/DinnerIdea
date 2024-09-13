//
//  InsightView.swift
//  ios
//
//  Created by Mateusz Zaremba on 13/09/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct InsightView: View {
    var body: some View {
        NavigationView {
            VStack {
                LoadingScreen()
            }
            .navigationBarTitle("Insight", displayMode: .inline)
            .background(AppColors.background.edgesIgnoringSafeArea(.all))
            .onAppear {
            }
            .onDisappear {
            }
        }
    }
}
