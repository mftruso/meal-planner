<%@ page import="com.trusowebdev.mealplanner.Dish; com.trusowebdev.mealplanner.DishType" %>
<html>
<head>
    <title>Edit Meal</title>
    <meta name="layout" content="bootstrap">
    <asset:script>
        var dishSearchUrl = "${createLink(controller: 'dish', action: 'searchDishes')}";
        var dishRemoveUrl = "${createLink(controller: 'meal', action: 'removeDishFromMeal')}";
        var mealId = ${meal.id};
    </asset:script>
    <asset:javascript src="app/meal/edit.js" />
</head>
<body>
    <h1>Edit ${meal.mealDate.format('MM/dd/yyyy')}</h1>

    <g:form action="update" name="editForm">
        <g:hiddenField name="id" value="${meal.id}" />
        <g:hiddenField name="dishes" />

        <div class="dish-list">
            <g:each in="${meal.dishes}" var="dish" >
                <div class="form-group dish-item">
                    <label>${dish.type.name} <a href="#" class="remove-dish" title="Remove Dish" data-remove-dish="${dish.id}"><i class="fa fa-remove"></i></a></label>
                    <g:select name="${dish.type.name.toLowerCase()}Dish" value="${dish.id}" class="form-control dish" from="${Dish.findAllByType(dish.type, [sort: 'name', order: 'asc'])}" optionValue="name" optionKey="id" noSelection="['':'Select a Dish']"  />
                </div>
            </g:each>
        </div>

        <div>
            <label for="addDish">Add Another Dish</label>
            <g:select name="addDish" from="${dishTypes}" optionValue="name" optionKey="id" noSelection="['':'Select a Type']" />
        </div>

        <div class="checkbox">
            <label for="groceryList">
                <g:checkBox name="groceryList" value="${meal.groceryList}" class="" />
                Grocery List
            </label>
        </div>

        <div class="checkbox">
            <label for="groceriesPurchased">
                <g:checkBox name="groceriesPurchased" value="${meal.groceriesPurchased}" class="" />
                Groceries Purchased
            </label>
        </div>

        <g:submitButton name="editSubmit" class="btn btn-primary" value="Save" />
        <a href="${createLink(controller: 'meal', action: 'list')}" class="btn btn-default">Cancel</a>
    </g:form>
</body>
</html>