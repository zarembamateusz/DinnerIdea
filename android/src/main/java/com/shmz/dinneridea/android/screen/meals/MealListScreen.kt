package com.shmz.dinneridea.android.screen.meals

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shmz.dinneridea.android.R
import com.shmz.dinneridea.android.screen.StartEffect
import com.shmz.dinneridea.android.screen.asString
import com.shmz.dinneridea.android.screen.components.ErrorScreen
import com.shmz.dinneridea.android.screen.components.ListItemSecondaryText
import com.shmz.dinneridea.android.screen.components.ListItemText
import com.shmz.dinneridea.android.screen.components.LoadingScreen
import com.shmz.dinneridea.android.screen.components.TitleAppBar
import com.shmz.dinneridea.domain.Meal
import com.shmz.dinneridea.domain.Meals
import com.shmz.dinneridea.screens.meals.MealListScreenState
import com.shmz.dinneridea.screens.meals.MealListViewModel

@Composable
fun MealListScreen(
    viewModel: MealListViewModel = androidx.lifecycle.viewmodel.compose.viewModel {
        MealListViewModel()
    }
) {
    StartEffect {
        viewModel.onStart()
    }
    MealListScreen(
        screenState = viewModel.screenState
    )
}

@Composable
fun MealListScreen(
    screenState: MealListScreenState
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TitleAppBar(title = R.string.meals.asString())
        },
        content = {
            Box(
                modifier = Modifier
                    .padding(top = it.calculateTopPadding(), bottom = 16.dp)
                    .fillMaxWidth()
            ) {
                when (screenState) {
                    is MealListScreenState.Error -> ErrorScreen(
                        errorMessage = screenState.errorMessage,
                        onRetry = screenState.onRetry
                    )

                    is MealListScreenState.Idle ->
                        MealList(meals = screenState.meals)

                    MealListScreenState.Loading -> LoadingScreen()
                }
            }
        }
    )
}

@Composable
private fun MealList(meals: Meals) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ListItemSecondaryText(text = R.string.listAvailableMeals.asString())
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(Modifier.fillMaxHeight()) {
            items(meals) { meal ->
                Meal(
                    meal = meal
                )
            }
        }
    }
}

@Composable
private fun Meal(meal: Meal) {
    Column(
        modifier = Modifier.padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ListItemText(text = meal.name)
    }
}
