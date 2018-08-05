<div class="g-signin2" data-onsuccess="onSignIn"></div>
<script>
    function onSignIn(googleUser) {
        var profile = googleUser.getBasicProfile();
        var id_token = googleUser.getAuthResponse().id_token

        var xhr = new XMLHttpRequest();
        xhr.open('POST', 'http://patataje.jjdd4.is-academy.pl/validation',true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.onload = function () {
            console.log('Signed in as: ' + xhr.responseText);
         //   window.location.reload();
        };
        xhr.send('idtoken=' + id_token);
    }
</script>