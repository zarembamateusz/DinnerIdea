package com.shmz.dinneridea.android.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.shmz.dinneridea.android.screen.insight.InsightScreen
import com.shmz.dinneridea.android.screen.meals.MealListScreen
import com.shmz.dinneridea.android.screen.settings.SettingsScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        route = "nav_host_route",
        navController = navController,
        startDestination = Destination.INSIGHT.route
    ) {
        composable(Destination.MEALS.route) {
            MealListScreen()
        }
        composable(Destination.INSIGHT.route) {
            InsightScreen()
        }
        composable(Destination.SETTINGS.route) {
            SettingsScreen()
        }
    }
}

enum class Destination(val route: String) {
    MEALS("meals"),
    INSIGHT("insight"),
    SETTINGS("settings");

    override fun toString(): String = route
}