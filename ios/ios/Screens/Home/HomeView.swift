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
    @State private var tappedTwice = false
    @State private var mealsTab = UUID()
    @State private var insightTab = UUID()
    @State private var settingsTab = UUID()

    private var handler: Binding<TabItem> { Binding(
        get: {
            self.selectedTab
        },
        set: {
            if $0 == self.selectedTab {
                tappedTwice = true
            }
            self.selectedTab = $0
        }
    )}

    var body: some View {
        TabView(selection: handler) {
            MealListView()
            .id(mealsTab)
            .onChange(of: tappedTwice, perform: { tappedTwice in
                guard tappedTwice && selectedTab == .meals else {
                    return
                }
                self.tappedTwice = false
            })
            .tabItem {
                Image(systemName: "list.clipboard")
                    .renderingMode(.template)
                Text("Meals")
                    .fontWeight(selectedTab == .meals ? .medium : .regular)
            }
            .background(TabBarAccessor { tabBar in
                        tabBar.unselectedItemTintColor = UIColor(AppColors.onPrimary)
                    })
            .tag(TabItem.meals)
            
            InsightView()
            .id(insightTab)
            .onChange(of: tappedTwice, perform: { tappedTwice in
                guard tappedTwice && selectedTab == .insight else {
                    return
                }
                self.tappedTwice = false
            })
            .tabItem {
                Image(systemName: "arrow.counterclockwise")
                    .renderingMode(.template)
                Text("Insight")
                    .fontWeight(selectedTab == .insight ? .medium : .regular)
            }
            .tag(TabItem.insight)

            SettingsView()
            .id(settingsTab)
            .onChange(of: tappedTwice, perform: { tappedTwice in
                guard tappedTwice && selectedTab == .settings else {
                    return
                }
                settingsTab = UUID()
                self.tappedTwice = false
            })
            .tabItem {
                Image(systemName: "gear")
                    .renderingMode(.template)
                Text("Settings")
                    .fontWeight(selectedTab == .settings ? .medium : .regular)
            }
            .tag(TabItem.settings)
        }
        .accentColor(AppColors.primary)
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
