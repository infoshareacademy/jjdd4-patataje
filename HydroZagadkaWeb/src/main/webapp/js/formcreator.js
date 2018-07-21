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
                    $('#watercontainer').append('<option value="-1">[Wybierz]</option>');
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
                    var history = response;

                    google.charts.load('current', {'packages':['corechart']});
                    google.charts.setOnLoadCallback(drawChart);
                    function drawChart() {

                        var data=[];
                        var Header= ['Dzień', 'Przepływ [m/s]', 'Temperatura [ C]','Poziom wody [cm]'];
                        data.push(Header);
                        for (var i = 0; i < history.length; i++) {
                            var temp=[];
                            temp.push(i);
                            temp.push(history[i].flow);
                            temp.push(history[i].temperature);
                            temp.push(history[i].waterDeep);
                            data.push(temp);
                        }
                        console.log(data)
                        var chartdata = new google.visualization.arrayToDataTable(data);

                        $('#curve_chart').fadeIn(2000);

                        var options = {
                            title: 'Pjęęękny wykres:',
                            curveType: 'function',
                            legend: { position: 'bottom' }
                        };

                        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

                        chart.draw(chartdata, options);
                    }




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