package com.trusowebdev.mealplanner

class Meal {

    Date mealDate
    String notes
    boolean groceryList = false
    boolean groceriesPurchased = false

    static hasMany = [dishes: Dish]

    static constraints = {
        mealDate nullable: false
        notes nullable: true
    }
}
