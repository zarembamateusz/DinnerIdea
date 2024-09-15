//
//  Extensions.swift
//  ios
//
//  Created by Mateusz Zaremba on 15/09/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
extension LocalizedStringResource {
    func toString() -> String {
        String(localized: self)
    }
}
