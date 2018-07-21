$(document).ready(function () {
    $('#province').select2();
    $('#watercontainer').select2();
    $('#station').select2();
    var mapchange=false;
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
        var rzeka=$("#watercontainer").val();
        $('.invalid-feedback').css("display","none");
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

        google.charts.load('current', {
            'packages': ['map'],
            // Note: you will need to get a mapsApiKey for your project.
            // See: https://developers.google.com/chart/interactive/docs/basic_load_libs#load-settings
            'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'
        });
        google.charts.setOnLoadCallback(drawMap);

        function drawMap() {
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Address');
            data.addColumn('string', 'Location');
            data.addRows([
                ['rzeka '+rzeka, rzeka]
                // ['rzeka Skotawa', 'Skotawa'],
                // ['rzeka Łupawa', 'Łupawa'],
                // ['rzeka Piaśnica Dolna', 'Piaśnica Dolna'],
                // ['rzeka Bolszewka', 'Bolszewka'],
                // ['rzeka Reda', 'Reda'],
                // ['Martwa Wisła', 'Martwa Wisła'],
                // ['rzeka Słupia', 'Słupia'],
                // ['rzeka Motława', 'Motława'],
                // ['rzeka Radunia', 'Radunia']
            ]);

            var options = {
                mapType: 'styledMap',
                zoomLevel: 12,
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
                url:'/history?station=' + historyId,
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
                    }else {
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
                                temp.push(i);
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
                                legend: {position: 'bottom'}
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