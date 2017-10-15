<%@ page import="com.trusowebdev.mealplanner.Meal" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap"/>
    <title>MealPlanner</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
</head>
<body>

Login: <oauth2:connect provider="google">google</oauth2:connect>


<h1>My Meal Planner</h1>

<h2>What's for dinner this week?</h2>

<ul>
    <li>Monday: </li>
    <li>Tuesday: </li>
    <li>Wednesday: </li>
    <li> ... </li>
</ul>

%{--TODO: marketing materials here--}%

</body>
</html>
