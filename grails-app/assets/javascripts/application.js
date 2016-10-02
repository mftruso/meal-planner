//= require jquery-2.2.0.min
//= require bootstrap
//= require moment
//= require fullcalendar
//= require_tree .
//= require_self

function intializeTypeahead(id, type) {

    $(document).ready(function(){
        var values = new Bloodhound({
          datumTokenizer: Bloodhound.tokenizers.whitespace,
          queryTokenizer: Bloodhound.tokenizers.whitespace,
          remote: {
              url: '/dish/searchDishes?q=%query&type='+type,
              wildcard: '%query'
            }
        });

        $("#"+id).typeahead(null, {
          name: 'id',
          display: 'name',
          source: values
        });

    });

}


