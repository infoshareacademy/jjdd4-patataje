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
                <div id="my-signin2"></div>
            </div>
            <div class="col"></div>
        </div>
    </div>

    <div class="overlay"></div>

    <script>
        function onSuccess(googleUser) {
            console.log('Logged in as: ' + googleUser.getBasicProfile().getName());
        }

        function onFailure(error) {
            console.log(error);
        }

        function renderButton() {
            gapi.signin2.render('my-signin2', {
                'scope': 'profile email',
                'width': 240,
                'height': 50,
                'longtitle': true,
                'theme': 'dark',
                'onsuccess': onSuccess,
                'onfailure': onFailure
            });
        }
    </script>
    <script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>

</section>