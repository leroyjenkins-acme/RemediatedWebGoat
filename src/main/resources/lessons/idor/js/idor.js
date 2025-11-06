// need custom js for this?

webgoat.customjs.idorViewProfile = function(data) {
    webgoat.customjs.jquery('#idor-profile').text(
        'name:' + data.name + '\n'+
        'color:' + data.color + '\n'+
        'size:' + data.size + '\n'
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