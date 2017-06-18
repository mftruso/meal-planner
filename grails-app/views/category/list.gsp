<%@ page import="com.trusowebdev.mealplanner.DishCategory; com.trusowebdev.mealplanner.Dish" %>
<html>
<head>
    <title>Categories</title>
    <meta name="layout" content="bootstrap">
    <asset:stylesheet src="app/category/list.css" />
</head>
<body>

<g:if test="${flash.message}">
<div class="row">
    <div class="col-xs-12">
        <div class="card card-warning">
            <div class="card-header">
                <div class="header-block">
                    ${flash.message}
                </div>
            </div>
            <div class="card-block">
                <g:eachError bean="${category}">
                    <li><g:message error="${it}" /></li>
                </g:eachError>
            </div>
        </div>
    </div>
</div>
</g:if>

<h1>Categories <a href="${createLink(action: 'create')}" class="btn btn-primary btn-small rounded"><i class="fa fa-plus"></i> New</a>
</h1>

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
                        <div class="header-block pull-right">
                            <div class="dropdown">
                                <a class="dropdown-toggle category-mini-menu" href="https://example.com" id="${category.name}DropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="fa fa-ellipsis-v"></i>
                                </a>

                                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="${category.name}DropdownMenuLink">
                                    <a href="${createLink(controller: 'category', action: 'edit', id: category.id)}" class="dropdown-item">
                                        <i class="fa fa-pencil"></i> Edit
                                    </a>
                                    <a href="${createLink(controller: 'category', action: 'delete', id: category.id)}" class="dropdown-item">
                                        <i class="fa fa-trash"></i> Delete
                                    </a>
                                </div>
                            </div>


                        </div>
                    </div>
                    <div class="card-block">
                        <div class="row">
                            <ul>
                                <g:each in="${DishCategory.findAllByCategory(category)}" var="dishCategory">
                                    <li><a href="${createLink(controller: 'dish', action: 'show', id: dishCategory.dish.id)}">${dishCategory.dish.name}</a></li>
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