//
//  MealListView.swift
//  ios
//
//  Created by Mateusz Zaremba on 13/09/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MealListView: View {
    @StateViewModel var viewModel = MealListViewModel()
    
    var body: some View {
        NavigationView {
            VStack(alignment: .leading) {
                if let state = viewModel.screenState as? MealListScreenStateIdle {
                    MealsView(
                        meals: state.meals
                    )
                } else if let state = viewModel.screenState as? MealListScreenStateError {
                    ErrorView(errorMessage: state.errorMessage, onRetry: state.onRetry)
                } else {
                    LoadingView()
                }
            }
            .frame(maxWidth: .infinity)
            .navigationBarTitle(LocalizedStringKey("meals"), displayMode: .inline)
            .foregroundColor(AppColors.onPrimary)
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

struct MealsView: View {
    let meals: [Meal]
    
    var body: some View {
        ScrollView {
            VStack(alignment: .leading) {
                Text(LocalizedStringKey("listAvailableMeals"))
                    .font(.subheadline)
                    .foregroundColor(AppColors.onSecondary)
            }
            .padding(.vertical, 8)
            ForEach(meals,  id: \.self) { meal in
                MealView(meal: meal)
            }
        }
    }
}
