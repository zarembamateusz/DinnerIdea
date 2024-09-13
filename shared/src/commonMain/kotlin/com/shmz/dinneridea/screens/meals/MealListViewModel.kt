package com.shmz.dinneridea.screens.meals

import com.shmz.dinneridea.repositories.MealRepository
import com.shmz.dinneridea.repositories.MealRepositoryImpl
import com.shmz.mvvm.StatefulViewModel
import com.shmz.mvvm.state
import kotlinx.coroutines.launch

class MealListViewModel(
    // TODO: Deliver repository by DI.
    private val mealRepository: MealRepository = MealRepositoryImpl()
) : StatefulViewModel() {

    var screenState: MealListScreenState by state(MealListScreenState.Loading)
        private set

    fun onStart() {
        loadMeals()
    }

    private fun loadMeals() {
        viewModelScope.launch {
            kotlin.runCatching {
                mealRepository.getMeals()
            }.onSuccess {
                screenState = MealListScreenState.Idle(meals = it)
            }.onFailure {
                screenState =
                    MealListScreenState.Error("Something went wrong") { onStart() }
            }
        }
    }
}
