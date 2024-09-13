package com.shmz.dinneridea.android.screen.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.shmz.dinneridea.android.AppColors
import com.shmz.dinneridea.android.R
import com.shmz.dinneridea.android.screen.Destination

@Composable
fun BottomBar(
    navController: NavHostController,
) {
    val navigationScreen = listOf(
        Destination.MEALS, Destination.INSIGHT, Destination.SETTINGS
    )

    NavigationBar(
        containerColor = AppColors.BOTTOM_BAR
    ) {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        navigationScreen.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                alwaysShowLabel = false,
                label = {
                    Text(
                        text = item.asTitle(),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(item.asIcon()),
                        contentDescription = null,
                        tint = (if (item.route == currentRoute) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary),
                    )
                },
                onClick = {
                    navController.navigate(item.route)
                }
            )
        }
    }
}

@Composable
fun Destination.asIcon(): Int =
    when (this) {
        Destination.MEALS -> R.drawable.ic_meals
        Destination.INSIGHT -> R.drawable.ic_insight
        else -> R.drawable.ic_settings
    }

fun Destination.asTitle(): String =
    when (this) {
        Destination.MEALS -> "Meals"
        Destination.INSIGHT -> "Insight"
        Destination.SETTINGS -> "Settings"
    }