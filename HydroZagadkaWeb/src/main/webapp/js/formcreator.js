$(document).ready(function () {
    $('#province').select2();
    $('#watercontainer').select2();
    $('#station').select2();
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
        if($("#province").val()==-1 || $("#watercontainer").val()==-1){
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
                    $('#station').append('<option value="-1">[Wybierz]</option>');
                    var options = JSON.parse(response);
                    for (var i = 0; i < options.length; i++) {
                        $('#station').append('<option value='+options[i].id+'>'+options[i].name+'</option>');
                    }
                    $('#stationlist').fadeIn(1000);

                },
                error:function(){
                    alert('error');
                }
            }
        );
    })

    $('.history-form').on('submit', function () {
        var historyId = $('#station').val()

        $.ajax({
                url:'/history?station=' + historyId,
                crossDomain: true,
                type:'get',
                success:function(response){
                    console.log(response)
                    console.log(response[0])
                },
                error:function(err){
                    console.log(err)
                    alert('error');
                }
            }
        )

        return false
    });
});