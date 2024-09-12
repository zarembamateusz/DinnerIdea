package com.shmz.dinneridea.domain

typealias Meals = List<Meal>

data class Meal(
    val id: String,
    val name: String
)

fun meals(): Meals = listOf(
    Meal(id = "1", name = "Tomato soup" ),
    Meal(id = "2", name = "Lasagna" ),
    Meal(id = "3", name = "Chicken parmesan" ),
    Meal(id = "4", name = "Pizzadillas" ),
    Meal(id = "5", name = "Chicken curry" ),
    Meal(id = "6", name = "Vegetarian Chili" ),
    Meal(id = "7", name = "BBQ chicken sliders" ),
    Meal(id = "8", name = "Carbonara" ),
    Meal(id = "9", name = "Easy enchiladas" ),
    Meal(id = "10", name = "Bourbon chicken" ),
)