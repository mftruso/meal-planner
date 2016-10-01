<%@ page import="com.trusowebdev.mealplanner.DishCategory; com.trusowebdev.mealplanner.DishType" %>
<html>
<head>
    <title>Edit Dish</title>
    <meta name="layout" content="bootstrap">
    <asset:script>
        $(document).ready(function() {
            var initialCategories = "${dish.categories.collect{it.id}}";
            initialCategories = initialCategories.slice(1, -1);
            $('input[name="categoryIds"]').val(initialCategories);
        });
    </asset:script>
    <asset:javascript src="categories.js" />
</head>
<body>
    <h1>Edit Dish: ${dish.name}</h1>
    <h4>${flash.message}</h4>
    <ul class="errors">
        <g:eachError bean="${dish}">
            <li><g:message error="${it}" /></li>
        </g:eachError>
    </ul>
    <g:form action="update" id="editDishForm">
        <div class="form-group row">
            <div class="col-md-8">
                <g:hiddenField name="dishId" value="${dish.id}" />
                <label>Name</label>
                <g:textField name="name" class="form-control" value="${dish.name}" />

                <label>Recipe Location</label>
                <g:textField name="recipeLocation" class="form-control" value="${dish.recipeLocation}" />

                <label>Notes</label>
                <g:textArea name="notes" class="form-control" value="${dish.notes}" />

            </div>
            <div class="col-md-4">
                <label>Type</label>
                <g:select name="type" class="form-control" value="${dish.type.id}" from="${DishType.list()}" optionValue="name" optionKey="id" />

                <label>Categories</label>
                <ul class="categoryList">
                    <g:each in="${dish.categories}" var="category">
                        <li>${category.name}<a href="#" id="${category.id}" class="removeCategoryItem"><i class="fa fa-remove"></i></a></li>
                    </g:each>
                </ul>
                <g:select name="category" class="form-control categorySelect" from="${DishCategory.listOrderByName()}" optionValue="name" optionKey="id" noSelection="['':'-Add a Category-']" />
                <g:hiddenField name="categoryIds" value="" />
            </div>
        </div>

        <div class="row">
            <g:submitButton name="submit" class="btn btn-primary" value="Save" />
        </div>
    </g:form>
</body>
</html>