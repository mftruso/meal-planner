package com.trusowebdev.mealplanner

import grails.converters.JSON

class DishController {

    def index() { }

    def list(){

    }

    def edit(){
        Dish dish = Dish.get(params.id)
        [
                dish: dish,
                categories: DishCategory.findAllByDish(dish).collect {it.category}
        ]
    }

    def searchDishes(){
        def responseData
        if(params.id){
            Dish dish =  Dish.get(params.id)
            def history = Meal.withCriteria() {
                dishes {
                    inList("id", [dish.id])
                }
            }.collect {
                it.mealDate.format('MM/dd/yyyy')
            }
            def dishDetails = [
                    name: dish.name,
                    type: dish.type.name,
                    recipeLocation: dish.recipeLocation,
                    notes: dish.notes,
                    history: history,
                    categories: DishCategory.findAllByDish(dish).collect { it.category.name }
            ]
            responseData = dishDetails
        } else if(params.q && params.type) {
            responseData = Dish.findAllByTypeAndNameIlike(DishType.findAllByNameIlike("%${params.type}%"),"%${params.q}%").collect {
                [
                        id:it.id,
                        name:it.name
                ]
            }
        } else if(params.q){
            responseData = Dish.findAllByNameIlike("%${params.q}%").collect {
                [
                        id: it.id,
                        name: it.name
                ]
            }
        } else {
            responseData = Dish.listOrderByName()
        }

        render responseData as JSON
    }

    def create() {

    }

    def save() {
        Dish dish = new Dish(params)
        def categories = params.categoryIds?.tokenize(',')
        categories.each {
            def category = Category.get(it)
            new DishCategory(dish: dish, category: category).save()
        }
        if(!dish.save()){
            flash.message = "Error saving new Dish!"
            render view: 'create', model: [dish: dish]
            return
        }
        flash.message = 'New Dish Saved!'
        redirect action: 'list'
    }

    def update(){
        Dish dish = Dish.get(params.dishId)
        dish.properties = params
        def categories = params.categoryIds?.tokenize(',')*.toLong()
        categories.each {
            def category = Category.get(it)
            DishCategory.findOrCreateByDishAndCategory(dish, category).save()
        }

        //remove unused
        DishCategory.withCriteria {
            and {
                eq ('dish', dish)
                category {
                    not { 'in' ('id', categories) }
                }
            }
        }.each {
            it.delete()
        }
        if(!dish.save()){
            flash.message = "Error saving new Dish!"
            render view: 'edit', model: [dish: dish]
            return
        }
        flash.message = 'Dish Saved!'
        redirect action: 'list'
    }

    def delete(){
        //TODO
    }
}
