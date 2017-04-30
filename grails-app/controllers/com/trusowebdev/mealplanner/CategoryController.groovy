package com.trusowebdev.mealplanner

class CategoryController {

    def index() {}

    def list(){
        def results = Category.list()
        [categories: results]
    }

    def create(){

    }

    def save() {
        Category category =  new Category(params)
        if(!category.save()){
            flash.message = "Error saving new Category!"
            render view: 'create', model: [category: category]
            return
        }
        flash.message = 'New Category Saved!'
        redirect action: 'list'
    }
}