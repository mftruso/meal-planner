<%@ page import="com.trusowebdev.mealplanner.DishCategory; com.trusowebdev.mealplanner.DishType" %>
<html>
<head>
    <title>New Dish</title>
    <meta name="layout" content="bootstrap">
    <asset:javascript src="categories.js" />
</head>
<body>
    <h1>New Dish</h1>
    <h4>${flash.message}</h4>
    <ul class="errors">
        <g:eachError bean="${dish}">
            <li><g:message error="${it}" /></li>
        </g:eachError>
    </ul>
    <g:form action="save" id="saveDishForm">
        <div class="form-group">
            <label>Name</label>
            <g:textField name="name" class="form-control" value="" />

            <label>Recipe Location</label>
            <g:textField name="recipeLocation" class="form-control" value="" />

            <label>Notes</label>
            <g:textArea name="notes" class="form-control" value="" />

            <label>Type</label>
            <g:select name="type" class="form-control" from="${DishType.list()}" optionValue="name" optionKey="id" />

            <label>Categories</label>
            <ul class="categoryList"></ul>
            <g:select name="category" class="form-control categorySelect" from="${DishCategory.listOrderByName()}" optionValue="name" optionKey="id" noSelection="['':'-Add a Category-']" />
            <g:hiddenField name="categoryIds" value="" />
        </div>
        <g:submitButton name="submit" class="btn btn-primary" value="Submit" />
    </g:form>
</body>
</html>