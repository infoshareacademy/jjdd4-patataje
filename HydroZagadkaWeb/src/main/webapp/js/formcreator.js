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
                    $('#watercontainer').find('option').remove();
                    var options = JSON.parse(response);
                    for (var i = 0; i < options.length; i++) {
                        $('#watercontainer').append('<option value='+options[i].name+'>'+options[i].name+'</option>');
                    }
                   $('#watercontainerlist').fadeIn(1000);

                },
                error:function(){
                    alert('error');
                }
            }
        );
    });



    $("#watercontainer").change(function () {
        if($("#province").val()==-1 || $("#watercontainer").val()==""){
            return;
        }
        $.ajax({
                url:'station',
                data:{
                    name:$("#province").val(),
                    watercontainer:$("#watercontainer").val()},
                type:'get',
                cache:false,
                success:function(response){
                    $('#station').find('option').remove();
                    var options = JSON.parse(response);
                    for (var i = 0; i < options.length; i++) {
                        $('#station').append('<option value='+options[i].name+'>'+options[i].name+'</option>');
                    }
                    $('#stationlist').fadeIn(1000);

                },
                error:function(){
                    alert('error');
                }
            }
        );
    });
});