$(document).ready(function () {
    login('Jerry');
})

function login(user) {
    $.ajax({
        type: 'POST',
        url: 'JWT/refresh/login',
        contentType: "application/json",
        data: JSON.stringify({user: user, password: "bm5nhSkxCXZkKRy4"})
    }).success(
        function (response) {
            // Store tokens in a more secure manner
            storeTokensSecurely(response['access_token'], response['refresh_token']);
        }
    )
}

// Function to store tokens securely
function storeTokensSecurely(accessToken, refreshToken) {
    // Example: Use cookies with HttpOnly and Secure flags
    document.cookie = "access_token=" + accessToken + "; path=/; Secure; HttpOnly";
    document.cookie = "refresh_token=" + refreshToken + "; path=/; Secure; HttpOnly";
}

//Dev comment: Pass token as header as we had an issue with tokens ending up in the access_log
webgoat.customjs.addBearerToken = function () {
    var headers_to_set = {};
    headers_to_set['Authorization'] = 'Bearer ' + getCookie('access_token');
    return headers_to_set;
}

// Function to retrieve cookies
function getCookie(name) {
    var value = "; " + document.cookie;
    var parts = value.split("; " + name + "=");
    if (parts.length == 2) return parts.pop().split(";").shift();
}

//Dev comment: Temporarily disabled from page we need to work out the refresh token flow but for now we can go live with the checkout page
function newToken() {
    getCookie('refresh_token');
    $.ajax({
        headers: {
            'Authorization': 'Bearer ' + getCookie('access_token')
        },
        type: 'POST',
        url: 'JWT/refresh/newToken',
        data: JSON.stringify({refreshToken: getCookie('refresh_token')})
    }).success(
        function () {
            // Store new tokens securely
            storeTokensSecurely(apiToken, refreshToken);
        }
    )
}