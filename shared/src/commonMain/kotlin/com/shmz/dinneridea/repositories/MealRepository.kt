package com.shmz.dinneridea.repositories

import com.shmz.dinneridea.domain.Meal
import com.shmz.dinneridea.domain.Meals
import com.shmz.dinneridea.domain.meals

interface MealRepository {
    suspend fun getMeals(): Meals
    suspend fun getRandomMeal(mealIdeas: Int): Meals
}

class MealRepositoryImpl : MealRepository {

    override suspend fun getMeals(): List<Meal> = meals()

    override suspend fun getRandomMeal(mealIdeas: Int): Meals = meals().shuffled().take(mealIdeas)
}
