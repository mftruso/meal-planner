package com.trusowebdev.mealplanner

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

@ReadOnly
class CategoryController {

    def index() {}

    def list(){
        def results = Category.listOrderByName()
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

    @Transactional
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

    @Transactional
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