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
    @StateViewModel var viewModel = InsightViewModel()
    
    var body: some View {
        NavigationView {
            VStack {
                if let state = viewModel.screenState as? InsightScreenStateIdle {
                    InsightDetailsView(
                        meals: state.meals,
                        loadMeal: viewModel.loadMeal
                    )
                } else if let state = viewModel.screenState as? InsightScreenStateError {
                    ErrorView(errorMessage: state.errorMessage, onRetry: state.onRetry)
                } else {
                    LoadingView()
                }
            }
            .frame(maxWidth: .infinity)
            .navigationBarTitle(LocalizedStringKey("insight"), displayMode: .inline)
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

struct InsightDetailsView: View {
    let meals: [Meal]
    let loadMeal: () -> Void
    
    var body: some View {
        ScrollView {
            VStack(alignment: .leading) {
                Text(LocalizedStringKey("yourIdeasForMeal"))
                    .font(.subheadline)
                    .foregroundColor(AppColors.onSecondary)
            }
            .padding(.vertical, 8)
            ForEach(meals,  id: \.self) { meal in
                MealView(meal: meal)
            }
            Button(action: {
                loadMeal()
            }) {
                Text(LocalizedStringKey("randomizeNewMeal"))
                    .padding()
                    .background(AppColors.primary)
                    .foregroundColor(AppColors.onPrimary)
                    .cornerRadius(8)
            }
            .padding(.vertical, 16)
        }
    }
}

struct MealView: View {
    let meal: Meal
    
    var body: some View {
        VStack(alignment: .leading) {
            VStack(alignment: .leading) {
                Text(meal.name)
                    .font(.body)
                    .foregroundColor(AppColors.onPrimary)
            }
            .padding(.vertical, 8)
        }
    }
}

