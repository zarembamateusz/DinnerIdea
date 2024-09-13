package com.shmz.dinneridea.screens.insight

import com.shmz.dinneridea.repositories.MealRepository
import com.shmz.dinneridea.repositories.MealRepositoryImpl
import com.shmz.dinneridea.storage.SharedStorage
import com.shmz.dinneridea.storage.SharedStorageImpl
import com.shmz.dinneridea.storage.SharedStorageKeys
import com.shmz.mvvm.StatefulViewModel
import com.shmz.mvvm.state
import kotlinx.coroutines.launch

class InsightViewModel(
    // TODO: Deliver repository by DI.
    private val mealRepository: MealRepository = MealRepositoryImpl(),
    private val sharedStorage: SharedStorage = SharedStorageImpl()
) : StatefulViewModel() {

    var screenState: InsightScreenState by state(InsightScreenState.Loading)
        private set

    fun onStart() {
        loadMeal()
    }

    fun loadMeal() {
        viewModelScope.launch {
            kotlin.runCatching {
                mealRepository.getRandomMeal((sharedStorage.getLong(SharedStorageKeys.DisplayedMealCount.name) ?: 1).toInt())
            }.onSuccess {
                screenState = InsightScreenState.Idle(meals = it)
            }.onFailure {
                screenState =
                    InsightScreenState.Error("Something went wrong") { onStart() }
            }
        }
    }
}
