$(document).ready(function () {
    $('#province').select2();
    $('#watercontainer').select2();
    $('#station').select2();
    var mapchange=false;
    $("#province").change(function () {
        if($("#province").val()==-1){
            return;
        }
        console.log("AJAX LUBUSKIE", $("#province").val());
        $.ajax({
                url:"rest/"+$("#province").val(),
                type:'get',
                cache:false,
                success:function(response){
                    $('#watercontainer').find('option').remove();
                    $('#watercontainer').append('<option value="-1">[Wybierz]</option>');
                    var options = response;
                    console.log(options);
                    for (var i = 0; i < options.length; i++) {
                        $('#watercontainer').append('<option value='+options[i].name+'>'+options[i].name+'</option>');
                    }
                   $('#watercontainerlist').fadeIn(1000);
                    $('#station').find('option').remove();
                    $('#station').append('<option value="-1">[Wybierz]</option>');
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
        var rzeka=$("#watercontainer").val();
        $('.invalid-feedback').css("display","none");
        $.ajax({
                url:'rest/'+$("#province").val()+"/"+$("#watercontainer").val(),
                type:'get',
                cache:false,
                success:function(response){
                    $('#station').find('option').remove();
                    var options = response;
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

        google.charts.load('current', {
            'packages': ['map'],
            // Note: you will need to get a mapsApiKey for your project.
            // See: https://developers.google.com/chart/interactive/docs/basic_load_libs#load-settings
            'mapsApiKey': 'AIzaSyDQlTwqbQzIesvDheiMg2T6AzoWXA54Pa4'
        });
        google.charts.setOnLoadCallback(drawMap);

        function drawMap() {
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Address');
            data.addColumn('string', 'Location');
            data.addRows([
                ['rzeka '+rzeka, rzeka]

            ]);

            var options = {
                mapType: 'styledMap',
                zoomLevel: 9,
                showTooltip: true,
                showInfoWindow: true,
                useMapTypeControl: true,
                maps: {
                    // Your custom mapTypeId holding custom map styles.
                    styledMap: {
                        name: 'Styled Map', // This name will be displayed in the map type control.
                        styles: [
                            {
                                featureType: 'poi.attraction',
                                stylers: [{color: '#fce8b2'}]
                            },
                            {
                                featureType: 'road.highway',
                                stylers: [{hue: '#0277bd'}, {saturation: -50}]
                            },
                            {
                                featureType: 'road.highway',
                                elementType: 'labels.icon',
                                stylers: [{hue: '#000'}, {saturation: 100}, {lightness: 50}]
                            },
                            {
                                featureType: 'landscape',
                                stylers: [{hue: '#259b24'}, {saturation: 10}, {lightness: -22}]
                            }
                        ]
                    }
                }
            };

            var map = new google.visualization.Map(document.getElementById('map_div'));
            map.draw(data, options);
            $('#mapa').fadeIn(2000);
        }
    })

    $('.history-form').on('submit', function () {
        var historyId = $('#station').val();
            var startdate = $('#startdate').val();
            var enddate = $('#enddate').val();

        $.ajax({
                url:'rest/id/'+historyId,
                data:{
                  startDate:startdate,
                  endDate:enddate
                },
                crossDomain: true,
                type:'get',
                success:function(response){
                    console.log(response)
                    if(response.length ==0){
                      $("#curve_chart").html("<h1>Nie znaleziono wyników</h1>").fadeIn(2000);
                      return;
                    }
                    else {
                        mapchange = true;
                        var history = response;
                        google.charts.load('current', {'packages': ['corechart']});
                        google.charts.setOnLoadCallback(drawChart);

                        function drawChart() {

                            var data = [];
                            var Header = ['Dzień', 'Przepływ [m/s]', 'Temperatura [ C]', 'Poziom wody [cm]'];
                            data.push(Header);
                            for (var i = 0; i < history.length; i++) {
                                var temp = [];
                                temp.push(i+1);
                                temp.push(history[i].flow);
                                temp.push(history[i].temperature);
                                temp.push(history[i].waterDeep);
                                data.push(temp);
                            }
                            var chartdata = new google.visualization.arrayToDataTable(data);

                            $('#curve_chart').fadeIn(2000);

                            var options = {
                                title: 'Wykres zmian temperatury, przepływu oraz stanu wody dla wybranej stacji:',
                                curveType: 'function',
                                legend: {position: 'bottom'},
                                hAxis: {
                                    minValue:1}
                            };
                            var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

                            chart.draw(chartdata, options);
                        }
                    }
                    },
                error:function(err){
                    alert('error');
                }
            }
        )
        return false
    });


    $('#checkdate').click(function() {
        $("#dates").toggle(this.checked);
    });
});