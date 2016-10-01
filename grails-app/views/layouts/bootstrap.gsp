<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Meal Planner"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <asset:stylesheet src="application.css" />
    <asset:stylesheet src="fullcalendar.print.css" media="print" />
    <asset:javascript src="application.js" />

    <g:layoutHead/>
</head>
<body>

<div class="navbar navbar-default navbar-static-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/#">
            </a>
        </div>
        <div class="navbar-collapse collapse" aria-expanded="false" style="height: 0.8px;">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${createLink(controller: 'meal', action: 'list')}">Meals</a></li>
                <li><a href="${createLink(controller: 'dish', action: 'list')}">Dishes</a></li>
                <li><a href="${createLink(controller: 'dishCategory', action: 'list')}">Categories</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="container">
    <g:layoutBody/>
</div>

<div class="footer" role="contentinfo"></div>

<asset:deferredScripts/>

</body>
</html>
