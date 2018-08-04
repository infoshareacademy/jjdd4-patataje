<!DOCTYPE html>
<html lang="pl-PL">

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description"
          content="Project of Web Application which shows water levels for lakes and rivers in Poland.">
    <meta name="author" content="JPPL">

    <title>HydroZagadka Panel Admina</title>
    <link rel="icon" href="../../../HydroZagadkaFront/img/Drop.png">

    <!-- Bootstrap Core CSS -->
    <link href="../../../HydroZagadkaFront/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="../../../HydroZagadkaFront/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet"
          type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic"
          rel="stylesheet" type="text/css">
    <link href="../../../HydroZagadkaFront/vendor/simple-line-icons/css/simple-line-icons.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet"/>
    <!-- Custom CSS -->
    <link href="../../../HydroZagadkaFront/css/stylish-portfolio.min.css" rel="stylesheet">
<#-- File Style Scripts-->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
            integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
            integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
            crossorigin="anonymous"></script>
</head>

<body id="page-top">
<!-- Navigation -->

<a class="menu-toggle rounded" href="#">
    <i class="fa fa-bars"></i>
</a>

<#include "navBarAdmin.ftlh">

<!-- Header -->
<section>
    <div class="container">
        <div class="row">
            <div class="col"><br></div>
        </div>
        <div class="content-section-heading text-center">

            <h3 class="text-secondary mb-0">Adminie</h3>
            <h2 class="mb-5">Co chcesz dziś zrobić?</h2>
        </div>
        <div class="row no-gutters">
            <div class="col"></div>
            <div class="col-4">
                <a class="portfolio-item " href="#upload">
              <span class="caption">
                <span class="caption-content">
                  <h2>Uaktualnij dane</h2>
                  <p class="mb-0">Załaduj plik .zip z danymi zbiorników.</p>
                </span>
              </span>
                    <img class="img-fluid" src="../../../HydroZagadkaFront/img/portfolio-7.jpg" alt="">
                </a>
            </div>

            <div class="col-4">
                <a class="portfolio-item" href="#users">
              <span class="caption">
                <span class="caption-content">
                  <h2>Lista użytkowników</h2>
                  <p class="mb-0">Zobacz listę użytkowników aplikacji.</p>
                </span>
              </span>
                    <img class="img-fluid" src="../../../HydroZagadkaFront/img/portfolio-2.jpg" alt="">
                </a>
            </div>
            <div class="col"></div>
        </div>

        <div class="row no-gutters">
            <div class="col"></div>
            <div class="col-4">
                <a class="portfolio-item" href="#stats">
              <span class="caption">
                <span class="caption-content">
                  <h2>Statystyki wyświetleń</h2>
                  <p class="mb-0">Sprawdź wyświetlenia stacji i województw.</p>
                </span>
              </span>
                    <img class="img-fluid" src="../../../HydroZagadkaFront/img/portfolio-6.jpg" alt="">
                </a>
            </div>
            <div class="col-4">
                <a class="portfolio-item" href="#upload">
              <span class="caption">
                <span class="caption-content">
                  <h2>Loggery</h2>
                  <p class="mb-0">Zobacz logi<br>
                      aplikacji.</p>
                </span>
              </span>
                    <img class="img-fluid" src="../../../HydroZagadkaFront/img/portfolio-4.jpg"
                         alt="Zczytaj logi aplikacji.">
                </a>
            </div>
            <div class="col"></div>
        </div>
    </div>
    <br>
</section>

<!-- upload plików -->
<section class="content-section bg-light" id="upload">
<#include "../changingParts/uploadFiles.ftlh">

</section>

<!-- Lista uzytkownikow -->
<section class="content-section container text-center my-auto" id="users">
    <h3>Lista użytkowników:</h3>
<#include "../adminPage/users.ftlh">
</section>

<!-- Ilosc klikniec na wojewodztwa/stacje -->
<section class="content-section bg-light container text-center my-auto" id="stats">
    <h3>Najczęściej wyświetlane</h3>
    <br>
    <div class="row">
        <div class="col"></div>
        <div class="col-4">
    <h4 >województwa:</h4>
        <#include "../adminPage/listOfProvincesWithVIews.ftlh">
        </div>
        <div class="col-4">
            <h4 >stacje pomiarowe:</h4>
        <#include "../adminPage/mostViewedStations.ftlh">
        </div>
        <div class="col"></div>
    </div>
</section>

<!-- Footer -->
<DIV>
<#include "../mainPartsOfPage/footer.ftlh">
</DIV>
<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded js-scroll-trigger" href="#page-top">
    <i class="fa fa-angle-up"></i>
</a>


<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<!-- Bootstrap core JavaScript -->
<script src="../../HydroZagadkaFront/vendor/jquery/jquery.min.js"></script>
<script src="../../HydroZagadkaFront/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
<!-- Plugin JavaScript -->
<script src="../../HydroZagadkaFront/vendor/jquery-easing/jquery.easing.min.js"></script>
<#-- linked inn script -->
<script type="text/javascript" src="https://platform.linkedin.com/badges/js/profile.js" async defer></script>
<!-- Custom scripts for this template -->
<script src="../../HydroZagadkaFront/js/stylish-portfolio.min.js"></script>
<script src="../../js/formcreator.js"></script>
<script type="text/javascript"
        src="../../HydroZagadkaFront/bootstrap-filestyle-2.1.0/src/bootstrap-filestyle.min.js"></script>
<script type="text/javascript">
    $('#input01').filestyle()
    $('#input001').filestyle({
        'placeholder': 'Placeholder test'
    });
    $('#input02').filestyle({
        text: 'My filestyle',
        dragdrop: false
    });

    $('#input03').filestyle({
        badge: true,
        input: false,
        btnClass: 'btn-primary',
        htmlIcon: '<span class="oi oi-folder"></span> '
    });

    $('#input04').filestyle({
        htmlIcon: '<span class="oi oi-folder"></span> ',
        text: ''
    });

    $('#input05').filestyle({
        btnClass: 'btn-outline-primary'
    });

    $('#input06').filestyle({
        onChange: function (param) {
            console.log(param)
            alert(param);
        }
    });

    $('#input08').filestyle({
        text: ' File',
        btnClass: 'btn-success'
    });

    $('#clear').click(function () {
        $('#input08').filestyle('clear');
    });

    $('#input09').filestyle({
        text: 'File',
        btnClass: 'btn-primary'
    });

    $('#toggleInput').click(function () {
        var fs = $('#input09');
        if (fs.filestyle('input'))
            fs.filestyle('input', false);
        else
            fs.filestyle('input', true);
    });

    $('#input11').filestyle({
        text: 'Multiple',
        btnClass: 'btn-danger'
    });

    $('#input13').filestyle({
        disabled: true
    });

    $('#input14').filestyle({
        buttonBefore: true
    });
    $('#input15').filestyle({
        size: 'lg'
    });
    $('#input16').filestyle({
        size: 'sm'
    });

    // nultiple initialize
    $('.test').filestyle({
        btnClass: 'btn-primary'
    });

    $('#countRed').on('click', function () {
        $('#input03').filestyle('badgeName', 'badge-danger');
    });

    $('#countToggle').on('click', function () {
        if ($('#input03').filestyle('badge'))
            $('#input03').filestyle('badge', false);
        else
            $('#input03').filestyle('badge', true);
    });
</script>
</body>

</html>