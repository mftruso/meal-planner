<%@ page import="com.trusowebdev.mealplanner.Dish; com.trusowebdev.mealplanner.DishType" %>
<html>
<head>
    <title>Meal List</title>
    <meta name="layout" content="bootstrap">

    <style>
        .fc-event, .fc-event-dot {
            background-color: inherit;
        }

        .fc-event, .fc-event:hover, .ui-widget .fc-event {
            color: #000000;
        }
        .fc-event {
            border: none;
        }
    </style>

    <asset:script>
        var selectedDate;
        $(document).ready(function() {

            var cal = $('#calendar').fullCalendar({
                events: '${createLink(controller: 'meal', action: 'listJSON')}',
                eventRender: function(event, element) {
                    //
                },
                dayClick: function(date, jsEvent, view) {
                    $('.selectedDate').text(date.format());
                    $('#mealDate').val(date.format())
                    $('#addMealModal').modal('show');
                },
                eventClick: function(calEvent, jsEvent, view) {
                    console.log(calEvent)
                    $.get('${createLink(controller: 'meal', action: 'search')}?mealId='+calEvent.mealId)
                            .done(function(data){
                                console.log('search success')
                                $('.selectedDate').text(calEvent.start.format())
                                console.log(data)
                                $('.dish-list').empty()
                                data.dishes.forEach(lookupDish);
                                $('#groceryList').prop("checked", data.groceryList)
                                $('#groceriesPurchased').prop("checked", data.groceriesPurchased)
                                $('.editButton').prop('href', '${createLink(controller: 'meal', action: 'edit')}?id='+calEvent.mealId)
                                $('#groceries').attr('data-mealid', calEvent.mealId)
                                $('#viewMealModal').modal('show');
                            })
                            .fail(function(data){
                                console.log('search fail')
                            });
                }
            });

            //TODO: Add an extra dish to a meal

            $('.meal-save-button').click(function(){
                var formData = {};
                var dishes = [];
                $('.dish').each(function(){
                    var dishId = $(this).val();
                    if(dishId.length > 0) {
                        dishes.push(dishId)
                    }
                });
                formData.mealDate = $('#mealDate').val();
                formData.dishes = dishes;
                $.ajax({
                    type: "POST",
                    url: "${createLink(controller: 'meal', action: 'create')}",
                    data: JSON.stringify(formData),
                    dataType: 'json',
                    contentType: 'application/json',
                    headers: {
                        'Accept': 'application/json'
                    }
                }).done(function(data){
                   console.log(data)
                    $('#addMealModal').modal('hide');
                    cal.fullCalendar( 'refetchEvents' );
                }).fail(function(){
                    alert('Oops! There was a problem making this meal.');
                });

                $.each($(".dish"),function(index,field){
                    $(field).val("");
                });

            });


            $('.groceryBox').click(function(){
                var data = {}
                data.checked = this.checked
                data.box = this.id

                var mealId = $('#groceries').attr('data-mealid')

                $.ajax({
                    type: "POST",
                    url: "${createLink(controller: 'meal', action: 'update')}?id="+mealId,
                    data: JSON.stringify(data),
                    dataType: 'json',
                    contentType: 'application/json',
                    headers: {
                        'Accept': 'application/json'
                    }
                }).done(function(data){
                    cal.fullCalendar( 'refetchEvents' );
                }).fail(function(){
                    alert('Oops! There was a problem updating this meal.');
                });

            });

        });

        function lookupDish(dish, index){
            $.get('${createLink(controller: 'dish', action: 'searchDishes')}?id='+dish.id)
                    .done(function (data) {
                        console.log('dish lookup successful');
                        $('.dish-list').append("<li>" + data.name + "</li>");
                    })
                    .fail(function () {
                        console.log('dish lookup failed');
                    })
        }

        <g:each in="${DishType.listOrderBySortOrder()}" var="dishType" >
            intializeTypeahead("${dishType.name}","${dishType.name}");
        </g:each>
    </asset:script>

</head>
<body>

<div class="row">
    <h1>Meals</h1>
    <div id="calendar"></div>
</div>

<div id="addMealModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Meal for: <span class="selectedDate"></span></h4>
            </div>
            <div class="modal-body">

                <h3>Dishes</h3>

                <form id="addMealForm">
                    <g:hiddenField type="hidden" name="mealDate" />

                    <g:each in="${DishType.listOrderBySortOrder()}" var="dishType" >
                        <div>
                            <label>${dishType.name}</label>
                            <br/>

                            <input id="${dishType.name}" class="form-control dish" name="${dishType.name.toLowerCase()}Dish"/>
                        </div>

                    </g:each>

                    <div class="form-group extraDish" style="display:none">
                        <label>Type</label>
                        <g:select name="dishType" class="form-control dishType" from="${DishType.listOrderBySortOrder()}" optionValue="name" optionKey="id" />
                        <label>Dish Name</label>
                        <g:select name="dishName" class="form-control" from="${Dish.listOrderByName()}" optionValue="name" optionKey="id" />
                        <hr />
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary meal-save-button">Save changes</button>
            </div>
        </div>
    </div>
</div>

<div id="viewMealModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Meal for: <span class="selectedDate"></span></h4>
            </div>
            <div class="modal-body">

                <h3>Dishes</h3>
                <div class="dish-list"></div>
                <div id="groceries">
                    <label>
                        <input type="checkbox" class="groceryBox" id="groceryList">
                        Grocery List Create
                    </label>
                    </br>
                    <label>
                        <input type="checkbox" class="groceryBox" id="groceriesPurchased">
                        Groceries Purchased
                    </label>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <a href="#" class="btn btn-primary editButton">Edit</a>
            </div>
        </div>
    </div>
</div>

</body>
</html>