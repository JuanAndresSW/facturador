/**
 * specific-use server gateway for managing session
 */
var Session = /** @class */ (function () {
    function Session() {
    }
    //request start of session to server and get session key
    Session.open = function (session) {
        //document.cookie = `session=${session}; max-age=1209600; path=/; Secure`;
        document.cookie = "username=".concat(session, "; max-age=1209600; path=/; Secure");
    };
    //check for the existance of valid session key in cookies
    Session.isAuthenticated = function () {
        if (this.getUsername() === 'niño') {
            return true;
        }
        return false;
    };
    //get the value of username cookie
    Session.getUsername = function () {
        var cookieArray = decodeURIComponent(document.cookie).split('; ');
        try {
            return cookieArray[0].substring("username=".length);
        }
        catch (NullPointerException) {
            return '';
        }
    };
    //delete server and local side key session
    Session.close = function () {
        var cookies = document.cookie.split(';');
        for (var _i = 0, cookies_1 = cookies; _i < cookies_1.length; _i++) {
            var cookie = cookies_1[_i];
            document.cookie = cookie + "=;expires=" + new Date(0).toUTCString();
        }
    };
    //set session key expire date to initial value
    Session.renew = function () {
        document.cookie = "username=".concat(this.getUsername(), "; max-age=1209600; path=/; Secure");
    };
    return Session;
}());
export default Session;
