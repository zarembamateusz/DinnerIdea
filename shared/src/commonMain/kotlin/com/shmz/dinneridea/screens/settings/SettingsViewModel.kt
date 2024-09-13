package com.shmz.dinneridea.screens.settings

import com.shmz.dinneridea.repositories.MealRepository
import com.shmz.dinneridea.repositories.MealRepositoryImpl
import com.shmz.dinneridea.storage.SharedStorage
import com.shmz.dinneridea.storage.SharedStorageImpl
import com.shmz.dinneridea.storage.SharedStorageKeys
import com.shmz.mvvm.StatefulViewModel
import com.shmz.mvvm.state
import kotlinx.coroutines.launch

class InsightListViewModel(
    // TODO: Deliver repository by DI.
    private val mealRepository: MealRepository = MealRepositoryImpl(),
    private val sharedStorage: SharedStorage = SharedStorageImpl()
) : StatefulViewModel() {

    var screenState: SettingsScreenState by state(SettingsScreenState.Loading)
        private set

    fun onStart() {
        getDisplayedNumberCount()
    }

    fun getDisplayedNumberCount() {
        viewModelScope.launch {
            kotlin.runCatching {
                sharedStorage.getLong(
                    key = SharedStorageKeys.DisplayedMealCount.name
                ) ?: 1
            }.onSuccess {
                screenState = SettingsScreenState.Idle(displayedMealsCount = it.toInt())
            }.onFailure {
                screenState =
                    SettingsScreenState.Error("Something went wrong") { getDisplayedNumberCount() }
            }
        }
    }

    fun changeDisplayedMealsCount(displayedMealsCount: Int) {
        screenState = SettingsScreenState.Loading
        viewModelScope.launch {
            kotlin.runCatching {
                sharedStorage.setLong(
                    key = SharedStorageKeys.DisplayedMealCount.name,
                    value = displayedMealsCount.toLong()
                )
            }.onSuccess {
                screenState = SettingsScreenState.Idle(displayedMealsCount = displayedMealsCount)
            }.onFailure {
                screenState =
                    SettingsScreenState.Error("Something went wrong") {
                        changeDisplayedMealsCount(
                            displayedMealsCount
                        )
                    }
            }
        }
    }
}
