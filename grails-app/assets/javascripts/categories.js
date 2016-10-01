$(document).ready(function() {
    $('.categorySelect').change(function(){
        var categoryName = $('.categorySelect option:selected').text();
        var categoriesInput = $('input[name="categoryIds"]');
        var categoryId = $(this).val();
        var categoryIdArray = categoriesInput.val();
        if(categoryIdArray){
            categoryIdArray = categoryIdArray.split(',');
            if(categoryIdArray.indexOf(""+categoryId) < 0){
                categoryIdArray.push(categoryId);
                categoriesInput.val(categoryIdArray)
                appendCategoryItem(categoryName, categoryId);
            }
        } else {
            categoriesInput.val(categoryId);
            appendCategoryItem(categoryName, categoryId);
        }
    });

    $('.removeCategoryItem').click(function(){
        removeCategoryItem($(this));
    });
});

function appendCategoryItem(name, id){
    $('.categoryList').append('<li>'+name+' <a href="#" class="removeCategoryItem" id="'+ id +'"><i class="fa fa-remove"></i></a></li>')
    $('#'+id).click(function(){
        removeCategoryItem($(this));
    });
}

function removeCategoryItem(selector){
    var id = selector.prop('id');
    selector.parent().hide();
    selector.parent().html('');
    var categoriesInput = $('input[name="categoryIds"]');
    var categoryIdArray = categoriesInput.val().split(',');
    var index = categoryIdArray.indexOf(id);
    categoryIdArray.splice(index, 1);
    categoriesInput.val(categoryIdArray);
}