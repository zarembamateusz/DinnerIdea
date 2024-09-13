package com.shmz.dinneridea.screens.settings

sealed interface SettingsScreenState {

    data object Loading : SettingsScreenState

    data class Idle(
        val displayedMealsCount: Int = 1
    ) : SettingsScreenState

    data class Error(
        val errorMessage: String,
        val onRetry: () -> Unit
    ) : SettingsScreenState
}