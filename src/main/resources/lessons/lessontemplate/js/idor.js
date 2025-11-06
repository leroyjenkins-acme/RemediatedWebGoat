// need custom js for this?

webgoat.customjs.idorViewProfile = function(data) {
    webgoat.customjs.jquery('#idor-profile').html(
        'name:' + webgoat.customjs.jquery('<div>').text(data.name).html() + '<br/>' +
        'color:' + webgoat.customjs.jquery('<div>').text(data.color).html() + '<br/>' +
        'size:' + webgoat.customjs.jquery('<div>').text(data.size).html() + '<br/>'
    );
}

var onViewProfile = function () {
    console.warn("on view profile activated")
    webgoat.customjs.jquery.ajax({
        method: "GET",
        url: "IDOR/profile",
        contentType: 'application/json; charset=UTF-8'
     }).then(webgoat.customjs.idorViewProfile);
}