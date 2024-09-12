package com.shmz.dinneridea.screens.meals

import com.shmz.dinneridea.domain.Meal

sealed interface MealListScreenState {

    data object Loading : MealListScreenState

    data class Idle(
        val meals: List<Meal>,
    ) : MealListScreenState

    data class Error(
        val errorMessage: String,
        val onRetry: () -> Unit
    ) : MealListScreenState
}