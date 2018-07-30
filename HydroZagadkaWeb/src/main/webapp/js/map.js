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
                    var Header= ['Dzień', 'Poziom wody [cm]', 'Przepływ [m/s]','Temperatura [ C]'];
                    data.push(Header);
                    for (var i = 0; i < history.length; i++) {
                        var temp=[];
                        temp.push(i);
                        temp.push(history[i][0]);
                        temp.push(history[i][1]);
                        temp.push(history[i][2]);
                        data.push(temp);
                    }
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