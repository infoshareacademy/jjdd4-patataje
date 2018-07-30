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
            console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
            console.log('Name: ' + profile.getName());
            console.log('Image URL: ' + profile.getImageUrl());
            console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
        }
    </script>

</section>