package com.shmz.dinneridea.screens.insight

import com.shmz.dinneridea.domain.Meals

sealed interface InsightScreenState {

    data object Loading : InsightScreenState

    data class Idle(
        val meals: Meals
    ) : InsightScreenState

    data class Error(
        val errorMessage: String,
        val onRetry: () -> Unit
    ) : InsightScreenState
}