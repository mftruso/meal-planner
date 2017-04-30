$(document).ready(function(){
    $('#editSubmit').click(function(e){
        e.preventDefault();
        var dishList = [];
        $('.dish').each(function(){
            dishList.push($(this).val());
        });
        $('#dishes').val(dishList);
        $('#editForm').submit();
    });

    $('#addDish').change(function() {
        var type = $(this).val();
        var options = '';
        $.get(dishSearchUrl + "?type=" + type).done(function(data){
            data.forEach(function(dish){
                options += '<option value="' + dish.id + '">' + dish.name + '</option>';
            });
            $('.dish-list').append('<label>New ' + type + '</label><select class="new-dish-select dish form-control">' + options + '</select>');
        });
    });

    $('.remove-dish').click(function(){
        var dishId = $(this).attr('data-remove-dish');
        $.post(dishRemoveUrl + '?mealId=' + mealId + '&dishId=' + dishId).done(function(data){
            console.log('Deleted Dish ' + dishId);
            //TODO: user feedback
            $(this).closest('.dish-item').html('');
        }.bind(this));
    })
});