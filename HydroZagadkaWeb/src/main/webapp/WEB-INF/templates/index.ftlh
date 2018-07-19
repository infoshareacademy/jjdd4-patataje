<#ftl encoding='UTF-8'>
<!DOCTYPE html>
<html lang="pl-PL">

<head>

    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description"
          content="Project of Web Application which shows water levels for lakes and rivers in Poland.">
    <meta name="author" content="JPPL">

    <title>HydroZagadka</title>
    <link rel="icon" href="../../HydroZagadkaFront/img/Drop.png">

    <!-- Bootstrap Core CSS -->
    <link href="../../HydroZagadkaFront/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="../../HydroZagadkaFront/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic"
          rel="stylesheet" type="text/css">
    <link href="../../HydroZagadkaFront/vendor/simple-line-icons/css/simple-line-icons.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet"/>
    <!-- Custom CSS -->
    <link href="../../HydroZagadkaFront/css/stylish-portfolio.min.css" rel="stylesheet">
</head>

<body id="page-top">
<!-- Navigation -->

<a class="menu-toggle rounded" href="#">
    <i class="fa fa-bars"></i>
</a>
<#include "mainPartsOfPage/navBar.ftlh">

<!-- Header -->
<header class="masthead d-flex">
    <div class="container text-center my-auto">
        <h1 class="mb-1">HydroZagadka</h1>
        <h3 class="mb-5">
            <em>Aplikacja sprawdzająca stan wód w Polsce</em>
        </h3>
        <a class="btn btn-primary btn-xl js-scroll-trigger" href="#upload">Wyślij dane!</a>
        <a class="btn btn-primary btn-xl js-scroll-trigger" href="#check">Sprawdź zbiornik!</a>
        <a class="btn btn-primary btn-xl js-scroll-trigger" href="#about">O aplikacji!</a>
    </div>
    <div class="overlay"></div>
</header>


<!-- upload plików -->
<#include "changingParts/uploadFiles.ftlh">

<!-- Sprawdź zbiornik -->
<#include "changingParts/checkContainer.ftlh">

<!-- wyświetlanie danych - wykresy -->
<#include "changingParts/charts.ftlh">



<!-- wyświetlanie danych - mapa -->


<section class="content-section bg-light" id="charts">
    <div class="container text-center my-auto">
        <div class="row">
            <div class="col-lg-10 mx-auto">
                <h2>  wykresy</h2>
                <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
                <script type="text/javascript">
                    google.charts.load('current', {'packages':['corechart']});
                    google.charts.setOnLoadCallback(drawChart);
                    function drawChart() {

                        // $.ajax({
                        //             url:'history',
                        //
                        //             type:'get',
                        //             success:function(response){
                        //                 var options = JSON.parse(response);
                        //             },
                        //             error:function(){
                        //                 alert('error');
                        //             }
                        //         }
                        // );


                        var data = google.visualization.arrayToDataTable([
                            ['Data', 'Poziom wody', 'Przepływ', 'Temperatura'],
                            ['2004',  10,      4, 12],
                            ['2005',  11,      4.60, 13],
                            ['2006',  6,       1.120, 15],
                            ['2007',  10,      5.40, 17]
                        ]);

                        var options = {
                            title: 'Pjęęękny wykres:',
                            curveType: 'function',
                            legend: { position: 'bottom' }
                        };

                        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

                        chart.draw(data, options);
                    }
                </script>
                <div id="curve_chart" style="width: 900px; height: 500px"></div>
            </div>
        </div>
    </div>
    <div class="overlay"></div>
</section>


<!-- Logowanie -->

<#include "mainPartsOfPage/logUser.ftlh">

<!-- o aplikacji -->
<#include "mainPartsOfPage/aboutApp.ftlh">

<!-- Kontakt -->
<#include "mainPartsOfPage/aboutAuthors.ftlh">

<!-- Footer -->

<#include "mainPartsOfPage/footer.ftlh">

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded js-scroll-trigger" href="#page-top">
    <i class="fa fa-angle-up"></i>
</a>

<!-- Bootstrap core JavaScript -->
<script src="../../HydroZagadkaFront/vendor/jquery/jquery.min.js"></script>
<script src="../../HydroZagadkaFront/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
<!-- Plugin JavaScript -->
<script src="../../HydroZagadkaFront/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for this template -->
<script src="../../HydroZagadkaFront/js/stylish-portfolio.min.js"></script>
<script src="../../js/formcreator.js"></script>
</body>

</html>