//
//  LoadingView.swift
//  ios
//
//  Created by Mateusz Zaremba on 13/09/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct LoadingView: View {
    var body: some View {
        HStack {
            ProgressView().progressViewStyle(.circular)
        }.background(AppColors.background.edgesIgnoringSafeArea(.all))
    }
}

