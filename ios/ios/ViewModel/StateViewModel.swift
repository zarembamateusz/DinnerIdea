//
//  StateViewModel.swift
//  ios
//
//  Created by Mateusz Zaremba on 13/09/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

@propertyWrapper
struct StateViewModel<ViewModel>: DynamicProperty where ViewModel: MvvmStatefulViewModel {
    @StateObject private var viewModelObservable: ObservableViewModel<ViewModel>

    init(wrappedValue: ViewModel) {
        _viewModelObservable = StateObject(wrappedValue: ObservableViewModel(wrappedValue))
    }

    var wrappedValue: ViewModel {
        get { viewModelObservable.viewModel }
        set { viewModelObservable.viewModel = newValue }
    }

    var projectedValue: ObservedObject<ObservableViewModel<ViewModel>>.Wrapper {
        self.$viewModelObservable
    }
}
