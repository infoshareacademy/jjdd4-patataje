<section class="content-section bg-light" id="maps">
    <div class="container text-center my-auto">
        <div class="row">
            <div class="col-lg-10 mx-auto">
                <h2> Mapa Polski:</h2>

                <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
                <script type="text/javascript">
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
                            ['Poland', 'TO JEST POLSKA'],
                            ['rzeka Skotawa', 'Skotawa'],
                            ['rzeka Łupawa', 'Łupawa'],
                            ['rzeka Piaśnica Dolna', 'Piaśnica Dolna'],
                            ['rzeka Bolszewka', 'Bolszewka'],
                            ['rzeka Reda', 'Reda'],
                            ['Martwa Wisła', 'Martwa Wisła'],
                            ['rzeka Słupia', 'Słupia'],
                            ['rzeka Motława', 'Motława'],
                            ['rzeka Radunia', 'Radunia'],
                            ['Martwa Szkarpawa', 'Szkarpawa'],
                            ['rzeka Tuga - Wielka Święta', 'Tuga - Wielka Święta'],
                            ['rzeka Liwa', 'Liwa'],
                            ['rzeka Cieplicówka', 'Cieplicówka'],
                            ['rzeka Wąska', 'Wąska'],
                            [' rzeka Dzierzgoń', 'Dzierzgoń'],
                            ['rzeka Tyna Górna', 'Tyna Górna'],
                            ['rzeka  Bauda', ' Bauda'],
                            ['rzeka Drwęca', 'Drwęca'],
                            ['rzeka Iławka', 'Iławka'],
                            [' rzeka Brda', 'Brda'],
                            ['rzeka Zbrzyca', 'Zbrzyca'],
                            ['rzeka  Wda', ' Wda'],
                            ['rzeka Zbrzyca', 'Zbrzyca'],
                            ['rzeka  Wierzyca', 'Wierzyca'],
                            ['rzeka  Wietcisa', ' Wietcisa']

                        ]);

                        var options = {
                            mapType: 'styledMap',
                            zoomLevel: 5,
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
                    }
                </script>
                <div id="map_div" style="height: 500px; width: 900px"></div>
            </div>
        </div>
    </div>
    <div class="overlay"></div>
</section>