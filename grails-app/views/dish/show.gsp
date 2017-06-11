<%@ page import="com.trusowebdev.mealplanner.Category; com.trusowebdev.mealplanner.DishType" %>
<html>
<head>
    <title>${dish.name}</title>
    <meta name="layout" content="bootstrap">
</head>
<body>
<h1>${dish.name} <a href="${createLink(controller: 'dish', action: 'edit', id: dish.id)}" class="btn btn-primary btn-small rounded"><i class="fa fa-pencil"></i> Edit</a></h1>

<div class="row">
    <div class="col-md-8">
        <div class="card">
            <div class="card-block">
                <div class="card-title-block">
                    <h3 class="title" >Details</h3>
                </div>
                <section class="section">
                    <h4>Type</h4>
                    <p>${dish.type.name}</p>

                    <h4>Recipe Location</h4>
                    <p>${dish.recipeLocation}</p>

                    <h4>Notes</h4>
                    <p>${dish.notes}</p>
                </section>
            </div>

        </div>
    </div>
    <div class="col-md-4">
        <div class="card">
            <div class="card-block">
                <div class="card-title-block">
                    <h3 class="title">Categories</h3>
                </div>
                <section class="section">
                    <ul class="categoryList">
                        <g:each in="${categories}" var="category">
                            <li><a href="${createLink(controller: 'category', action: 'show', id: category.id)}">${category.name}</a></li>
                        </g:each>
                    </ul>
                </section>
            </div>
        </div>
    </div>
</div>

<h2>History</h2>
<div class="row sameheight-container">
    <g:each in="${history}" var="meal">
        <div class="col-md-3">
            <div class="card sameheight-item stats">
                <div class="card-block">
                    <div class="card-title-block">
                        <h3 class="title">${meal.mealDate.format('MM/dd/yyyy')}</h3>
                    </div>
                    <section class="section">
                        <ul>
                            <g:each in="${meal.dishes}" var="mealDish">
                                <li>${mealDish.name}</li>
                            </g:each>
                        </ul>
                    </section>
                </div>
            </div>
        </div>
    </g:each>
</div>

</body>
</html>