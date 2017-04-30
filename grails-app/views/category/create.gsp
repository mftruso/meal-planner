<html>
<head>
    <title>Category</title>
    <meta name="layout" content="bootstrap">
</head>
<body>
    <h1>New Category</h1>

<section class="section">
    <div class="row sameheight-container">
        <div class="col col-xs-6">
            <div class="card sameheight-item stats" data-exclude="xs">
                <div class="card-block">
                    <div class="title-block">
                        <h4 class="title">
                            Categories
                        </h4>
                    </div>
                    <g:form action="save" id="saveCategoryForm" lpformnum="13">
                        <div class="form-group">
                            <label>Name</label>
                            <g:textField name="name" class="form-control" value="" />

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