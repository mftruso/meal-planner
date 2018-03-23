package com.trusowebdev.mealplanner

import grails.converters.JSON
import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

@ReadOnly
class DishController {

    def dishService

    def index() {

    }

    def list() {

    }

    def show(Dish dish){
        [
                dish: dish,
                categories: DishCategory.findAllByDish(dish).collect {it.category},
                history: dishService.getDishHistory(dish)
        ]
    }

    def edit(){
        Dish dish = Dish.get(params.id)
        [
                dish: dish,
                categories: DishCategory.findAllByDish(dish).collect {it.category},
        ]
    }

    def lookup(Dish dish) {
        def history = dishService.getDishHistory(dish).collect {
            it.mealDate.format('MM/dd/yyyy')
        }
        def dishDetails = [
                name          : dish.name,
                type          : dish.type.name,
                recipeLocation: dish.recipeLocation,
                notes         : dish.notes,
                history       : history,
                categories    : DishCategory.findAllByDish(dish).collect { it.category.name }
        ]
        render dishDetails as JSON
    }

    def searchDishes(DishSearchCommand cmd){
        def responseData
        List<Dish> dishes = dishService.search(cmd)
        responseData = dishes.collect {
            [
                    id: it.id,
                    name: it.name
            ]
        }
        render responseData as JSON
    }

    def listByCategory(Category category) {
        List dishes = DishCategory.findAllByCategory(category).collect { it.dish }.unique()
        render dishes as JSON
    }

    def create() {

    }

    @Transactional
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

    @Transactional
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
