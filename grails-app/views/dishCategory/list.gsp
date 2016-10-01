<html>
<head>
    <title>Categories</title>
    <meta name="layout" content="bootstrap">
</head>
<body>
    <h1>Categories</h1>
    <h4>${flash.message}</h4>
    <div class="col-md-8">
        <ul>
            <g:each in="${categories}" var="category">
                <li>${category.name}</li>
            </g:each>
        </ul>
    </div>
    <div class="col-md-4">
        <a href="${createLink(action: 'create')}" class="btn btn-primary"><i class="icon glyphicon-plus"></i> New</a>
    </div>
</body>
</html>