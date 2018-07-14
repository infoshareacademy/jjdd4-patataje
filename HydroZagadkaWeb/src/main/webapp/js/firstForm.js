$(document).ready(function() {
    $("#province").change(function(){
        if($(this).val() >= "0")
        {
            $(this).submit();
            $("#watercontainer").change(function(){
                if($(this).val() >= "0")
                {
                    $( "#watercontainer" ).submit();
                  $("#station").fadeIn(1000);
                }
            });
        }
    });

});