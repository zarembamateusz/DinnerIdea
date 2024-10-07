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
            VStack(alignment: .leading) {
                if let state = viewModel.screenState as? SettingsScreenStateIdle {
                    SettingsDetailsView(
                        displayedMealsCount: state.displayedMealsCount,
                        changeDisplayedMealsCount: viewModel.changeDisplayedMealsCount,
                        onClearFocus: { dismissKeyboard() }
                    )
                } else if let state = viewModel.screenState as? SettingsScreenStateError {
                    ErrorView(errorMessage: state.errorMessage, onRetry: state.onRetry)
                } else {
                    LoadingView()
                }
            }
            .background(AppColors.background.edgesIgnoringSafeArea(.all))
            .onAppear {
                viewModel.onStart()
            }
            .onDisappear {
                viewModel.onClear()
            }
        }
    }
    
    private func dismissKeyboard() {
        UIApplication.shared.sendAction(#selector(UIResponder.resignFirstResponder), to: nil, from: nil, for: nil)
    }
}

struct SettingsDetailsView: View {
    
    @State private var mealsCount: String = ""
    @State private var presentError: Bool = false
    
    init(displayedMealsCount: Int32, changeDisplayedMealsCount: @escaping (Int32) -> Void, onClearFocus: @escaping () -> Void) {
        self.mealsCount = String(displayedMealsCount)
        self.presentError = false
        self.changeDisplayedMealsCount = changeDisplayedMealsCount
        self.onClearFocus = onClearFocus
    }
    let changeDisplayedMealsCount: (Int32) -> Void
    let onClearFocus: () -> Void

    var body: some View {
        ScrollView {
            VStack(alignment: .leading) {
                VStack(alignment: .leading) {
                    Text(LocalizedStringKey("mealIdeasCountQuestion"))
                        .font(.subheadline)
                        .foregroundColor(AppColors.onSecondary)
                }
                .padding(.vertical, 8)
                TextField("", text: $mealsCount)
                    .keyboardType(.decimalPad)
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                Button(action: {
                    onClearFocus()
                    validateAndSave()
                }) {
                    Text(LocalizedStringKey("save"))
                        .frame(maxWidth: .infinity)
                        .padding()
                        .background(AppColors.primary)
                        .foregroundColor(AppColors.onPrimary)
                        .cornerRadius(8)
                }
                .padding(.vertical, 16)
                if presentError {
                    Text(LocalizedStringKey("invalidCountMealIdeas"))
                        .foregroundColor(.red)
                }
            }
            .padding()
        }
    }

    private func validateAndSave() {
        guard let count = Int32(mealsCount) else {
            presentError = true
            return
        }
        presentError = false
        changeDisplayedMealsCount(count)
    }
}
