package com.trusowebdev.mealplanner

class DishCategory {
    String name

    static constraints = {
        name nullable: false, unique: true
    }
}