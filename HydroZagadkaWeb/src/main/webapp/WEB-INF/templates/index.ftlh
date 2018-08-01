<#ftl encoding='UTF-8'>
<!DOCTYPE html>
<html lang="pl-PL">

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description"
          content="Project of Web Application which shows water levels for lakes and rivers in Poland.">
    <meta name="author" content="JPPL">
    <meta name="google-signin-client_id" content="YOUR_CLIENT_ID.apps.googleusercontent.com">

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
<div id="fb-root"></div>
<script>(function (d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s);
    js.id = id;
    js.src = 'https://connect.facebook.net/pl_PL/sdk.js#xfbml=1&version=v3.0&appId=293962678012596&autoLogAppEvents=1';
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>

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
        <a class="btn btn-primary btn-xl js-scroll-trigger" href="#check">Sprawdź zbiornik!</a>
        <a class="btn btn-primary btn-xl js-scroll-trigger" href="#about">O aplikacji!</a>
    </div>
    <div class="overlay"></div>
</header>

<#-- dane Zalogowanego uzytkownika-->
<#--<#include "changingParts/userDetailsAndFavs.ftlh">-->

<!-- o aplikacji -->
<#include "mainPartsOfPage/aboutApp.ftlh">

<!-- Sprawdź zbiornik -->
<#include "changingParts/checkContainer.ftlh">

<!-- wyświetlanie danych - wykresy -->

<#include "changingParts/charts.ftlh">

<!-- wyświetlanie danych - mapa -->
<#include "changingParts/maps.ftlh">

<!-- Kontakt -->
<#include "mainPartsOfPage/aboutAuthors.ftlh">

<!-- Footer -->
<#include "mainPartsOfPage/footer.ftlh">

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