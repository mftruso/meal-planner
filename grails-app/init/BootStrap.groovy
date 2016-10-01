import com.trusowebdev.mealplanner.Dish
import com.trusowebdev.mealplanner.DishCategory
import com.trusowebdev.mealplanner.DishType
import com.trusowebdev.mealplanner.Meal

class BootStrap {

    def init = { servletContext ->
        DishType mainType = new DishType(name: 'Main', sortOrder: 1).save(failOnError: true)
        DishType vegType = new DishType(name: 'Vegetable', sortOrder: 2).save(failOnError: true)
        DishType starchType = new DishType(name: 'Starch', sortOrder: 3).save(failOnError: true)
        DishType dessertType = new DishType(name: 'Dessert', sortOrder: 4).save(failOnError: true)
        DishType otherType = new DishType(name: 'Other', sortOrder: 5).save(failOnError: true)

        DishCategory category1 = new DishCategory(name: 'Pasta').save(failOnError: true)
        DishCategory category2 = new DishCategory(name: 'Pizza').save(failOnError: true)
        DishCategory category3 = new DishCategory(name: 'Italian').save(failOnError: true)
        DishCategory category4 = new DishCategory(name: 'Meat').save(failOnError: true)
        DishCategory category5 = new DishCategory(name: 'Mexican').save(failOnError: true)

        Dish main = new Dish(name: 'Lasagna', recipeLocation: 'Better Homes pg 113', type: mainType)
        main.addToCategories(category1)
        main.addToCategories(category3)
        main.save(failOnError: true)
        Dish starch = new Dish(name: 'Mashed Potatoes', recipeLocation: 'Better Homes pg 99', type: starchType).save(failOnError: true)
        Dish veg = new Dish(name: 'Creamed Corn', type: vegType).save(sfailOnError: true)
        Dish dessert = new Dish(name: 'Pie', recipeLocation: 'Better Homes pg 2', type: dessertType).save(failOnError: true)

        Meal meal = new Meal(mealDate: new Date())
        meal.addToDishes(main)
        meal.addToDishes(starch)
        meal.addToDishes(veg)
        meal.addToDishes(dessert)
        meal.save(failOnError: true)
    }
    def destroy = {
    }
}
