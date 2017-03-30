/**
 * Created by heowc on 2017-03-30.
 */
$(function () {

    init();

    $('#btn-social-facebook').on('click', function () {
        console.log('facebook-login');
        FB.login(function(response) {
            if (response.status === 'connected') {
                statusChangeCallback(response);
            } else {
            }
        }, {scope: 'public_profile,email'});
    });
});

function init() {
    window.fbAsyncInit = function() {
        FB.init({
            appId      : '802051679897614',
            cookie     : true,  // enable cookies to allow the server to access
                                // the session
            xfbml      : true,  // parse social plugins on this page
            version    : 'v2.8' // use graph api version 2.8
        });
    };

    (function(d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) return;
        js = d.createElement(s); js.id = id;
        js.src = "//connect.facebook.net/en_US/sdk.js";
        fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));
}

function statusChangeCallback(response) {
    console.log('statusChangeCallback');
    console.log(response);
    if (response.status === 'connected') {
        // login success
    } else {
        document.getElementById('status').innerHTML = 'Please log ' + 'into this app.';
    }
}
