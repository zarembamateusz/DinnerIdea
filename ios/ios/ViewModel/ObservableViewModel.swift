//
//  ObservableViewModel.swift
//  ios
//
//  Created by Mateusz Zaremba on 13/09/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

class ObservableViewModel<ViewModel>: ObservableObject where ViewModel: MvvmStatefulViewModel {
    var viewModel: ViewModel

    init(_ viewModel: ViewModel) {
        self.viewModel = viewModel
        self.viewModel.binder.objectWillChange = { [weak self] in
            DispatchQueue.main.async {
                self?.objectWillChange.send()
            }
        }
    }

    deinit {
        viewModel.onClear()
    }
}
