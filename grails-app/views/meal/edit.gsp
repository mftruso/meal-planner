<%@ page import="com.trusowebdev.mealplanner.Dish; com.trusowebdev.mealplanner.DishType" %>
<html>
<head>
    <title>Edit Meal</title>
    <meta name="layout" content="bootstrap">
</head>
<body>
    <h1>Edit ${meal.mealDate}</h1>

    <g:form action="update">
        <g:each in="${meal.dishes}" var="dish" >
            <label>${dish.type.name}</label>
            <g:select name="${dish.type.name.toLowerCase()}Dish" value="${dish.id}" class="form-control dish" from="${Dish.findAllByType(dish.type, [sort: 'name', order: 'asc'])}" optionValue="name" optionKey="id" noSelection="['':'Select a Dish']"  />
        </g:each>
    </g:form>
</body>
</html>