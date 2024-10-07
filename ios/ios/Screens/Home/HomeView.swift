//
//  HomeView.swift
//  ios
//
//  Created by Mateusz Zaremba on 13/09/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import shared
import SwiftUI

extension HomeView {
    private enum TabItem {
        case meals
        case insight
        case settings
    }
}

struct HomeView: View {
    @State private var selectedTab: TabItem = .insight
    @State private var mealsTab = UUID()
    @State private var insightTab = UUID()
    @State private var settingsTab = UUID()

    private var handler: Binding<TabItem> { Binding(
        get: {
            self.selectedTab
        },
        set: {
            self.selectedTab = $0
        }
    )}

    var body: some View {
        TabView(selection: handler) {
            MealListView()
            .id(mealsTab)
            .tabItem {
                Image(systemName: "list.clipboard")
                    .renderingMode(.template)
                if selectedTab == .meals {
                    Text(LocalizedStringKey("meals"))
                        .fontWeight(selectedTab == .meals ? .medium : .regular)
                }
            }
            .background(TabBarAccessor { tabBar in
                        tabBar.unselectedItemTintColor = UIColor(AppColors.onPrimary)
                    })
            .tag(TabItem.meals)
            
            InsightView()
            .id(insightTab)
            .tabItem {
                Image(systemName: "arrow.counterclockwise")
                    .renderingMode(.template)
                if selectedTab == .insight {
                    Text(LocalizedStringKey("insight"))
                        .fontWeight(.medium)
                }
            }
            .tag(TabItem.insight)

            SettingsView()
            .id(settingsTab)
            .tabItem {
                Image(systemName: "gear")
                    .renderingMode(.template)
                if selectedTab == .settings {
                    Text(LocalizedStringKey("settings"))
                        .fontWeight(.medium)
                }
            }
            .tag(TabItem.settings)
        }
        .accentColor(AppColors.onPrimary)
        .background(AppColors.background.edgesIgnoringSafeArea(.all))
    }
}

struct TabBarAccessor: UIViewControllerRepresentable {
    var callback: (UITabBar) -> Void
    private let proxyController = ViewController()

    func makeUIViewController(context: UIViewControllerRepresentableContext<TabBarAccessor>) ->
                              UIViewController {
        proxyController.callback = callback
        return proxyController
    }
    
    func updateUIViewController(_ uiViewController: UIViewController, context: UIViewControllerRepresentableContext<TabBarAccessor>) {
    }
    
    typealias UIViewControllerType = UIViewController

    private class ViewController: UIViewController {
        var callback: (UITabBar) -> Void = { _ in }

        override func viewWillAppear(_ animated: Bool) {
            super.viewWillAppear(animated)
            if let tabBar = self.tabBarController {
                self.callback(tabBar.tabBar)
            }
        }
    }
}
