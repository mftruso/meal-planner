<html>
<head>
    <title>Dish List</title>
    <meta name="layout" content="bootstrap">
    <asset:script>
        $(document).ready(function(){
            $.get( "${createLink(controller: 'dish', action: 'searchDishes')}", function( data ) {
                updateDishList(data)
            });

            $('.search').keyup(function () {
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
            var dishDetailUrl = "${createLink(controller: 'dish', action: 'searchDishes')}?id=" + id;
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
            });
        }
    </asset:script>
</head>
<body>
    <div class="row">
        <div class="col-md-8">
            <h1>Dishes</h1>
        </div>
        <div class="col-md-4">
            <a href="${createLink(controller: 'dish', action: 'create')}" class="btn btn-primary"><i class="icon glyphicon-plus"></i> New</a>
        </div>
    </div>

    <div class="row">
        <label>Search</label>
        <input type="search" class="form-control search" name="search" />
    </div>

    <div class="row">
        <div class="col-md-8 dishList"></div>
        <div class="col-md-4">
            <h2>Details</h2>
            <h3 class="dishName"></h3>

            <dd class="dishPropertyLabel">Recipe Location</dd>
            <dt class="dishPropertyValue dishLocation">&nbsp;</dt>

            <dd class="dishPropertyLabel">Dish Type</dd>
            <dt class="dishPropertyValue dishType">&nbsp;</dt>

            <dd class="dishPropertyLabel">Notes</dd>
            <dt class="dishPropertyValue dishNotes">&nbsp;</dt>

            <dd class="dishPropertyLabel">Categories</dd>
            <dt class="dishPropertyValue"><ul class="categoryList"></ul></dt>

            <h3>History</h3>
            <ul class="historyList"></ul>

            <div class="editButtonContainer" style="display:none;">
                <a href="#" class="btn btn-primary editButton">Edit</a>
            </div>
        </div>
    </div>
</body>
</html>