<html>
<head>
    <title>Category</title>
    <meta name="layout" content="bootstrap">
</head>
<body>


<g:if test="${flash.message}">
    <div class="row">
        <div class="col-xs-12">
            <div class="card card-warning">
                <div class="card-header">
                    <div class="header-block">
                        Errors:
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

<h1>${category?.name ? "Update $category.name" : "New"} Category</h1>

<section class="section">
    <div class="row sameheight-container">
        <div class="col col-xs-6">
            <div class="card sameheight-item stats" data-exclude="xs">
                <div class="card-block">
                    <div class="title-block">
                        <h4 class="title">
                            Category
                        </h4>
                    </div>
                    <g:form action="save" id="saveCategoryForm" lpformnum="13">
                        <g:hiddenField name="existingCategoryId" value="${category?.id}" />
                        <div class="form-group">
                            <label>Name</label>
                            <g:textField name="name" class="form-control" value="${category?.name}" />

                        </div>
                        <g:submitButton name="submit" class="btn btn-primary" value="Submit" />
                    </g:form>
                </div>
            </div>
        </div>
    </div>
</section>


</body>
</html>