<section class="masthead d-flex" id="log" style="background-image: url('../../HydroZagadkaFront/img/bg-masthead.jpg')">
    <div class="container my-auto">
        <div class="row">
            <div class="col"></div>
            <div class="col"><h3>Zaloguj się do aplikacji:</h3></div>
            <div class="col"></div>
        </div>
        <div class="row">
            <div class="col"></div>
            <div class="col">
                <div class="g-signin2" data-onsuccess="onSignIn"></div>
            </div>
            <div class="col"></div>
        </div>
    </div>

    <div class="overlay"></div>

    <script>
        function onSignIn(googleUser) {
            var profile = googleUser.getBasicProfile();
            var id_token = googleUser.getAuthResponse().id_token

            var xhr = new XMLHttpRequest();
            xhr.open('POST', 'http://localhost:8080/validation',true);
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            xhr.onload = function () {
                console.log('Signed in as: ' + xhr.responseText);
            };
            xhr.send('idtoken=' + id_token);
        }
    </script>

</section>