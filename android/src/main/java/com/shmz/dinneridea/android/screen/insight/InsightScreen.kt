package com.shmz.dinneridea.android.screen.insight

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.shmz.dinneridea.android.screen.StartEffect
import com.shmz.dinneridea.android.screen.components.ErrorScreen
import com.shmz.dinneridea.android.screen.components.ListItemSecondaryText
import com.shmz.dinneridea.android.screen.components.ListItemText
import com.shmz.dinneridea.android.screen.components.LoadingScreen
import com.shmz.dinneridea.android.screen.components.TitleAppBar
import com.shmz.dinneridea.domain.Meal
import com.shmz.dinneridea.domain.Meals
import com.shmz.dinneridea.screens.insight.InsightScreenState
import com.shmz.dinneridea.screens.insight.InsightViewModel

@Composable
fun InsightScreen(
    viewModel: InsightViewModel = androidx.lifecycle.viewmodel.compose.viewModel {
        InsightViewModel()
    }
) {
    StartEffect {
        viewModel.onStart()
    }
    InsightScreen(
        screenState = viewModel.screenState,
        loadMeal = viewModel::loadMeal
    )
}

@Composable
fun InsightScreen(
    screenState: InsightScreenState,
    loadMeal: () -> Unit
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TitleAppBar(title = "Insight")
        },
        content = {
            Box(modifier = Modifier.padding(top = it.calculateTopPadding(), bottom = 16.dp).fillMaxWidth()) {
                when (screenState) {
                    is InsightScreenState.Error -> ErrorScreen(
                        errorMessage = screenState.errorMessage,
                        onRetry = screenState.onRetry
                    )

                    is InsightScreenState.Idle -> InsightDetails(
                        meals = screenState.meals,
                        loadMeal = loadMeal
                    )

                    InsightScreenState.Loading -> LoadingScreen()
                }
            }
        }
    )
}

@Composable
private fun InsightDetails(meals: Meals, loadMeal: () -> Unit) {
    Column(
        modifier = Modifier.padding(16.dp).fillMaxWidth().verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(modifier = Modifier.padding(vertical = 8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            ListItemSecondaryText(text = "Your ideas for meal:")
        }
        meals.forEach { meal ->
            Meal(meal)
        }
        Spacer(modifier = Modifier.height(50.dp))
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colorScheme.primary),
            onClick = loadMeal
        ) {
            Text(
                text = "Randomize a new meal",
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
private fun Meal(meal: Meal) {
    Column(modifier = Modifier.padding(vertical = 8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        ListItemText(text = meal.name)
    }
}

