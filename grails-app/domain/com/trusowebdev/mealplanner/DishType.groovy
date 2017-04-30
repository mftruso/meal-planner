package com.trusowebdev.mealplanner

class DishType {
    String name
    int sortOrder

    static constraints = {
        name nullable: false, unique: true
        sortOrder nullable: false, min: 1
    }

    static mapping = {
        sort sortOrder: "asc"
    }
}