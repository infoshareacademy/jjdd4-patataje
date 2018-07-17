$(document).ready(function () {
    $("#province").change(function () {
        if($("#province").val()==-1){
            return;
        }
        $.ajax({
                url:'watercontainer',
                data:{name:$("#province").val()},
                type:'get',
                cache:false,
                success:function(response){
                    var options = JSON.parse(response);
                    for (var i = 0; i < options.length; i++) {
                        $('#watercontainer').append('<option value='+options[i].id+'>'+options[i].name+'</option>');
                    }
                    $('#watercontainerlist').fadeIn(1000);
                },
                error:function(){
                    alert('error');
                }
            }
        );
    });
});