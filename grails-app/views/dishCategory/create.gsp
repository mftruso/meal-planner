<html>
<head>
    <title>Category</title>
    <meta name="layout" content="bootstrap">
</head>
<body>
    <h1>New Category</h1>
    <h4>${flash.message}</h4>
    <ul class="errors">
        <g:eachError bean="${category}">
            <li><g:message error="${it}" /></li>
        </g:eachError>
    </ul>
    <g:form action="save" id="saveCategoryForm">
        <div class="form-group">
            <label>Name</label>
            <g:textField name="name" class="form-control" value="" />

        </div>
        <g:submitButton name="submit" class="btn btn-primary" value="Submit" />
    </g:form>
</body>
</html>