package com.shmz.dinneridea.repositories

import com.shmz.dinneridea.domain.Meal
import com.shmz.dinneridea.domain.Meals
interface MealRepository {
    suspend fun getMeals(): Meals
    suspend fun getRandomMeal(): Meal
}

class MealRepositoryImpl : MealRepository {
    override suspend fun getMeals(): List<Meal> {
        TODO("Not yet implemented")
    }

    override suspend fun getRandomMeal(): Meal {
        TODO("Not yet implemented")
    }
}
