package com.trusowebdev.mealplanner

class CategoryController {

    def index() {}

    def list(){
        def results = Category.list()
        [categories: results]
    }

    def create(){

    }

    def edit(){
        Category category = Category.get(params.id)
        if(!category){
            render view: "/notFound"
            return
        }

        render view: 'create', model: [category: category]
    }

    def save() {
        Category category

        if(params.existingCategoryId){
            category = Category.get(params.existingCategoryId)
            category.name = params.name
        } else {
            category =  new Category(params)
        }

        if(!category.save()){
            flash.message = "Error saving Category!"
            render view: 'create', model: [category: category]
            return
        }
        flash.message = 'Category Saved!'
        redirect action: 'list'
    }

    def delete(){
        Category category = Category.get(params.id)
        String name = category.name
        // unassign all dishes assigned to this category
        DishCategory.findAllByCategory(category).each{
            it.delete(flush: true)
        }
        category.delete()
        flash.message = "$name Deleted"
        redirect action: 'list'
    }

}