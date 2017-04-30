package com.trusowebdev.mealplanner

class Dish {

    String name
    String recipeLocation
    DishType type
    String notes

    static constraints = {
        name nullable: false, blank: false
        recipeLocation nullable: true
        notes nullable: true
    }
}


