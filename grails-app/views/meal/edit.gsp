<%@ page import="com.trusowebdev.mealplanner.Dish; com.trusowebdev.mealplanner.DishType" %>
<html>
<head>
    <title>Edit Meal</title>
    <meta name="layout" content="bootstrap">
    <asset:script>
        $(document).ready(function(){
            $('#editSubmit').click(function(e){
                e.preventDefault();
                var dishList = [];
                $('.dish').each(function(){
                    dishList.push($(this).val());
                });
                $('#dishes').val(dishList);
                $('#editForm').submit();
            });
        });
    </asset:script>
</head>
<body>
    <h1>Edit ${meal.mealDate.format('MM/dd/yyyy')}</h1>

    <g:form action="update" name="editForm">
        <g:hiddenField name="id" value="${meal.id}" />
        <g:hiddenField name="dishes" />

        %{--TODO: Sort by DishType--}%
        <g:each in="${meal.dishes}" var="dish" >
            <label>${dish.type.name}</label>
            <g:select name="${dish.type.name.toLowerCase()}Dish" value="${dish.id}" class="form-control dish" from="${Dish.findAllByType(dish.type, [sort: 'name', order: 'asc'])}" optionValue="name" optionKey="id" noSelection="['':'Select a Dish']"  />
        </g:each>
        %{--TODO: Be able to add Dishes, one for each DishType--}%

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