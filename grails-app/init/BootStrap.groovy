import com.trusowebdev.mealplanner.DishType

class BootStrap {

    def init = { servletContext ->
        if(!DishType.count()) {
            DishType mainType = new DishType(name: 'Main', sortOrder: 1).save(failOnError: true)
            DishType vegType = new DishType(name: 'Vegetable', sortOrder: 2).save(failOnError: true)
            DishType starchType = new DishType(name: 'Starch', sortOrder: 3).save(failOnError: true)
            DishType dessertType = new DishType(name: 'Dessert', sortOrder: 4).save(failOnError: true)
            DishType otherType = new DishType(name: 'Other', sortOrder: 5).save(failOnError: true)
        }
    }
    def destroy = {
    }
}
