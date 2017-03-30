/**
 * Created by heowc on 2017-03-30.
 */

function post(url, data, callback) {
    $.ajax({
        type   : "POST",
        url    : url,
        data   : JSON.stringify(data),
        success: callback,
        error  : function () {
            console.log();
        }
    });
}

function get(url, callback) {
    $.ajax({
        type   : "GET",
        url    : url,
        success: callback,
        error  : function () {
            console.log();
        }
    });
}