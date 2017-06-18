package com.trusowebdev.mealplanner

import grails.converters.JSON
import org.springframework.http.HttpStatus

class MealController {

    def index() {

    }

    def list() {

    }

    def edit() {
        [meal: Meal.get(params.id)]
    }

    def listJSON(){
        def events = []
        def meals = Meal.list()
        meals.each { meal ->
            def dishes = meal.dishes
            def title = dishes.sort{ a, b -> a.type.sortOrder <=> b.type.sortOrder }.collect {"${it.type.name}: ${it.name}"}.join(', ')
            def event = [
                    start: meal.mealDate.format('yyyy-MM-dd'),
                    allDay: true,
                    title: title,
                    mealId: meal.id
            ]
            events.add(event)
        }
        render events as JSON
    }

    def create(){
        def json = request.JSON
        Meal meal = new Meal()
        meal.mealDate = Date.parse("yyyy-MM-dd", json.mealDate)
        json.dishes.each {
            Dish dish = Dish.findByName(it)
            if(dish){
                meal.addToDishes(dish)
            }
        }
        if(!meal.save()){
            render (status: HttpStatus.BAD_REQUEST, message: meal.errors) as JSON
        } else {
            response.status = HttpStatus.ACCEPTED.value()
            render meal as JSON
        }
    }

    def search() {
        def response
        if(params.mealId){
            response = Meal.get(params.mealId)
        } else {
            response = [status: HttpStatus.BAD_REQUEST]
        }
        render response as JSON
    }

    def update(){
        def json = request.JSON
        Meal meal = Meal.get(params.id)

        if(json){
            if(!meal){
                render (status: HttpStatus.BAD_REQUEST, message: meal.errors) as JSON
            }
            else{
                def box = json.box
                def checked = json.checked

                if(box == "groceryList")
                    meal.groceryList = checked

                if(box == "groceriesPurchased")
                    meal.groceriesPurchased = checked

                if(!meal.save()){
                    render (status: HttpStatus.BAD_REQUEST, message: meal.errors) as JSON
                } else {
                    render (status: HttpStatus.ACCEPTED) as JSON
                }

            }
        } else {
            if(!meal){
                redirect action: 'list'
                return
            }

            meal.dishes = []

            def dishes = []
            if(params.dishes){
                dishes = params.dishes.split(',')
            }

            if(!dishes){
                meal.delete()
                flash.message = 'Meal Deleted'
                redirect action: 'list'
                return
            } else {
                dishes.each {
                    meal.addToDishes(Dish.get(it))
                }
            }

            meal.groceriesPurchased = params?.groceriesPurchased ? true : false
            meal.groceryList = params?.groceryList ? true : false

            if(meal.save()){
                flash.message = 'Successfully saved meal edits!'
                redirect action: 'list'
            } else {
                render view: 'edit', model: [meal: meal]
            }
        }
    }

    def removeDishFromMeal(){
        Meal meal = Meal.get(params.mealId)
        Dish dish = Dish.get(params.dishId)
        meal.removeFromDishes(dish)
        if(!meal.save()){
            render (status: HttpStatus.BAD_REQUEST, message: meal.errors) as JSON
        } else {
            render (status: HttpStatus.ACCEPTED) as JSON
        }
    }
}
