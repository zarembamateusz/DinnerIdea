package com.shmz.dinneridea.screens.insight

import com.shmz.dinneridea.repositories.MealRepository
import com.shmz.dinneridea.repositories.MealRepositoryImpl
import com.shmz.mvvm.StatefulViewModel
import com.shmz.mvvm.state
import kotlinx.coroutines.launch

class InsightListViewModel(
    // TODO: Deliver repository by DI.
    private val mealRepository: MealRepository = MealRepositoryImpl()
) : StatefulViewModel() {

    var screenState: InsightScreenState by state(InsightScreenState.Loading)
        private set

    fun onStart() {
        loadMeal()
    }

    fun loadMeal() {
        viewModelScope.launch {
            kotlin.runCatching {
                mealRepository.getRandomMeal()
            }.onSuccess {
                screenState = InsightScreenState.Idle(meal = it)
            }.onFailure {
                screenState =
                    InsightScreenState.Error("Something went wrong") { onStart() }
            }
        }
    }
}
