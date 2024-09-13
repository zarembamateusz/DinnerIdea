//
//  ObservedViewModel.swift
//  ios
//
//  Created by Mateusz Zaremba on 13/09/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

@propertyWrapper
struct ObservedViewModel<ViewModel>: DynamicProperty where ViewModel: MvvmStatefulViewModel {
    @ObservedObject private var viewModelObservable: ObservableViewModel<ViewModel>

    init(wrappedValue: ViewModel) {
        self.viewModelObservable = ObservableViewModel(wrappedValue)
    }

    var wrappedValue: ViewModel {
        get { viewModelObservable.viewModel }
        set { viewModelObservable.viewModel = newValue }
    }

    var projectedValue: ObservedObject<ObservableViewModel<ViewModel>>.Wrapper {
        self.$viewModelObservable
    }
}
