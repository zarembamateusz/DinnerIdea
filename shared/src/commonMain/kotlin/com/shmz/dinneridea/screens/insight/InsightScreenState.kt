package com.shmz.dinneridea.screens.insight

import com.shmz.dinneridea.domain.Meal

sealed interface InsightScreenState {

    data object Loading : InsightScreenState

    data class Idle(
        val meal: Meal
    ) : InsightScreenState

    data class Error(
        val errorMessage: String,
        val onRetry: () -> Unit
    ) : InsightScreenState
}