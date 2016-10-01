package com.trusowebdev.mealplanner


class DishCategoryController {

    def index() {}

    def list(){
        [categories: DishCategory.listOrderByName()]
    }

    def create(){

    }

    def save() {
        DishCategory category =  new DishCategory(params)
        if(!category.save()){
            flash.message = "Error saving new Category!"
            render view: 'create', model: [category: category]
            return
        }
        flash.message = 'New Category Saved!'
        redirect action: 'list'
    }

}