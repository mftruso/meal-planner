package com.trusowebdev.mealplanner

class Category {
    String name

    static constraints = {
        name nullable: false, unique: true
    }

    static mapping = {
        id generator: 'native', params: [sequence: 'seq_category']
    }
}
