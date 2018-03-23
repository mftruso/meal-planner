package com.trusowebdev.mealplanner

import grails.validation.Validateable

class DishSearchCommand implements Validateable {
    String q
    DishType type

    static constraints = {
        q nullable: true
        type nullable: true
    }
}
