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
<body style="">

<div class="main-wrapper">
    <div class="app" id="app">
        <header class="header">
            <div class="header-block header-block-collapse hidden-lg-up">
                <button class="collapse-btn" id="sidebar-collapse-btn">
                    <i class="fa fa-bars"></i>
                </button>
            </div>
            <div class="header-block header-block-search hidden-sm-down">
                <form role="search">
                    <div class="input-container"> <i class="fa fa-search"></i> <input type="search" placeholder="Search">
                        <div class="underline"></div>
                    </div>
                </form>
            </div>
        </header>
        <g:render template="/layouts/sidebar" />

        <article class="content dashboard-page">
            <g:render template="/layouts/alerts" />
            <g:layoutBody/>
        </article>
        <g:render template="/layouts/footer" />
    </div>
</div>

<asset:deferredScripts/>

</body>
</html>
