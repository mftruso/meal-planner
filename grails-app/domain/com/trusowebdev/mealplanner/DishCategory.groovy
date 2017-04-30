package com.trusowebdev.mealplanner

class DishCategory {
    Dish dish
    Category category

    static constraints = {
        dish nullable: false
        category nullable: false
    }

    static mapping = {
        dish sort: 'name'
    }
}