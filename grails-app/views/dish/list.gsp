<html>
<head>
    <title>Dish List</title>
    <meta name="layout" content="bootstrap">
    <asset:script type="application/javascript">
        $(document).ready(function(){
            $.get( "${createLink(controller: 'dish', action: 'searchDishes')}", function( data ) {
                updateDishList(data)
            });

            $('#searchBar').keyup(function () {
                var searchString = $(this).val();
                var searchUrl = "${createLink(controller: 'dish', action: 'searchDishes')}?q=" + searchString;
                $.get(searchUrl, function( data ) {
                    updateDishList(data);
                });
            });
        });

        function updateDishList(data){
            $('.dishList').html('');
            var dishHtml = "<ul>";
            for(var i =0; i < data.length; i++){
                var dish = data[i];
                dishHtml += "<li class='dish'><a href='#' class='dishLink' id='" + dish.id + "'>" + dish.name + "</a></li>";
            }
            dishHtml += "</ul>";
            $('.dishList').html(dishHtml);
            $('.dishLink').click(function(){
                var dishId = $(this).prop('id');
                loadDishDetails(dishId);
            });
        }

        function loadDishDetails(id){
            var dishDetailUrl = "${createLink(controller: 'dish', action: 'lookup')}?id=" + id;
            var dishEditUrl = "${createLink(controller: 'dish', action: 'edit')}/" + id;
            $('.historyList').html('');
            $('.categoryList').html('');
            $.get(dishDetailUrl , function( data ) {
                $('.dishName').text(data.name);
                $('.dishLocation').text(data.recipeLocation);
                $('.dishNotes').text(data.notes);
                $('.dishType').text(data.type);
                for(var i = 0; i < data.history.length; i++){
                    $('.historyList').append("<li>"+data.history[i]+"</li>");
                }
                for(var j = 0; j < data.categories.length; j++){
                    $('.categoryList').append("<li>"+data.categories[j]+"</li>");
                }
                $('.editButton').prop('href', dishEditUrl);
                $('.editButtonContainer').show();
                setSameHeights();
            });
        }
    </asset:script>
</head>
<body>

<h1>Dishes</h1>

<section class="section">
    <div class="row sameheight-container">
        <div class="col col-xs-6">
            <div class="card" data-exclude="xs">
                <div class="card-header">
                    <div class="header-block">
                        <h4 class="title">
                            Dishes
                        </h4>
                    </div>
                    <div class="header-block pull-right">
                        <a href="${createLink(controller: 'dish', action: 'create')}" class="btn btn-primary btn-sm rounded"><i class="fa fa-plus"></i> New</a>
                    </div>
                </div>
                <div class="card-block dishList">

                </div>
            </div>
        </div>

        <div class="col col-xs-6">
            <div class="card sameheight-item stats" data-exclude="xs">
                <div class="card-header">
                    <div class="header-block">
                        <h4 class="title">
                            Details
                        </h4>
                    </div>
                    <div class="header-block pull-right">
                        <div class="editButtonContainer" style="display:none;">
                            <a href="#" class="btn btn-primary btn-sm rounded editButton"><i class="fa fa-edit"></i> Edit</a>
                        </div>
                    </div>
                </div>
                <div class="card-block">
                    <h3 class="dishName"></h3>

                    <dd class="dishPropertyLabel">Recipe Location</dd>
                    <dt class="dishPropertyValue dishLocation">&nbsp;</dt>

                    <dd class="dishPropertyLabel">Dish Type</dd>
                    <dt class="dishPropertyValue dishType">&nbsp;</dt>

                    <dd class="dishPropertyLabel">Notes</dd>
                    <dt class="dishPropertyValue dishNotes">&nbsp;</dt>

                    <dd class="dishPropertyLabel">Categories</dd>
                    <dt class="dishPropertyValue"><ul class="categoryList"></ul></dt>
                </div>
            </div>

            <div class="card sameheight-item stats" data-exclude="xs">
                <div class="card-header">
                    <div class="header-block">
                        <h4 class="title">
                            History
                        </h4>
                    </div>
                </div>
                <div class="card-block">
                    <ul class="historyList"></ul>
                </div>
            </div>
        </div>
    </div>
</section>

</body>
</html>