<%@ page import="com.trusowebdev.mealplanner.DishCategory; com.trusowebdev.mealplanner.Dish" %>
<html>
<head>
    <title>Categories</title>
    <meta name="layout" content="bootstrap">
</head>
<body>
<h1>Categories</h1>
<a href="${createLink(action: 'create')}" class="btn btn-primary btn-small rounded"><i class="fa fa-plus"></i> New</a>

<section class="section">
    <div class="row sameheight-container">
        <g:each in="${categories}" var="category">
            <div class="col col-xs-6">
                <div class="card sameheight-item stats" data-exclude="xs">
                    <div class="card-header">
                        <div class="header-block">
                            <h4 class="title">
                                ${category.name}
                            </h4>
                        </div>
                    </div>
                    <div class="card-block">
                        <div class="row">
                            <ul>
                                <g:each in="${DishCategory.findAllByCategory(category)}" var="dishCategory">
                                    <li>${dishCategory.dish.name}</li>
                                </g:each>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </g:each>
    </div>
</section>

</body>
</html>