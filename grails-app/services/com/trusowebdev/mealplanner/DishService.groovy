package com.trusowebdev.mealplanner

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional
import grails.util.TypeConvertingMap

@Transactional
class DishService {

    @ReadOnly
    List<Dish> search(DishSearchCommand cmd) {
        Dish.createCriteria().list() {
            if (cmd.q) {
                ilike('name', "%${cmd.q}%")
            }
            if (cmd.type) {
                eq('type', cmd.type)
            }
        } as List
    }

    @ReadOnly
    List<Meal> getDishHistory(Dish dish){
        Meal.createCriteria().list {
            dishes {
                inList("id", [dish.id])
            }
            order ("mealDate", "desc")
        } as List
    }
}
