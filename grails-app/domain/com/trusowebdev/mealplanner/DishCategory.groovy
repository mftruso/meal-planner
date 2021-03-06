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
        id generator: 'native', params: [sequence: 'seq_dish_category']
    }
}
