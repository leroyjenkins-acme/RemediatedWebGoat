function startFollowing(user) {
    $.ajax({
        type: 'POST',
        url: 'JWT/kid/follow/' + encodeURIComponent(user)
    }).then(function (result) {
        $("#toast").text(result);
    })
}