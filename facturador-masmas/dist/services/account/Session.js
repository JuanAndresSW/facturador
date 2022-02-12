import authenticate from './authenticate';
/**
* Controlador local de la sesión de usuario.
*/
var Session = /** @class */ (function () {
    function Session() {
    }
    /**
     * establece los valores obtenidos de un login exitoso
     * @param token El token de la sesión.
     * @param name El nombre de usuario.
     * @param passive La cantidad de patrimonio pasivo.
     * @param active La cantidad de patrimonio activo.
     */
    Session.setSession = function (token, name, active, passive) {
        document.cookie = "session=".concat(token, "; max-age=1209600; path=/; Secure");
        sessionStorage.setItem("username", name);
        sessionStorage.setItem("active", active.toString());
        sessionStorage.setItem("passive", passive.toString());
    };
    /** Evalúa si existe un token de sesión válido en las cookies.*/
    Session.authenticate = function (callback) {
        var cookieArray = decodeURIComponent(document.cookie).split("; ");
        var token = cookieArray[0].substring("session=".length);
        authenticate(token, callback);
    };
    /** Borra la sesión.*/
    Session.close = function () {
        var cookies = document.cookie.split(";");
        for (var _i = 0, cookies_1 = cookies; _i < cookies_1.length; _i++) {
            var cookie = cookies_1[_i];
            document.cookie = cookie + "=;expires=" + new Date(0).toUTCString();
        }
        sessionStorage.clear();
    };
    //GETTERS
    Session.getUsername = function () {
        return sessionStorage.getItem('username') === null ?
            '?' : sessionStorage.getItem('username');
    };
    Session.getActive = function () {
        return sessionStorage.getItem('active') === null ?
            '?' : '$' + sessionStorage.getItem('active');
    };
    Session.getPassive = function () {
        return sessionStorage.getItem('passive') === null ?
            '?' : '$' + sessionStorage.getItem('passive');
    };
    Session.getNetWorth = function () {
        if (sessionStorage.getItem('passive') === null || sessionStorage.getItem('active') === null) {
            return '?';
        }
        return '$' + (parseFloat(Session.getActive()) -
            parseFloat(Session.getPassive()))
            .toPrecision(2).toString();
    };
    return Session;
}());
export default Session;
