import fetch from 'interceptors/fetch';
/**
 * Servicio de sesión de usuario. Permite obtener y solicitar los tokens
 * de acceso y de refrescamiento.
 */
var Session = /** @class */ (function () {
    function Session() {
    }
    /**
    * Trata de iniciar sesión con el token almacenado.
    * @param {Function} callback - La función que maneja la respuesta.
    */
    Session.getByToken = function (callback) {
        var accessToken = this.getAccessToken();
        var refreshToken = this.getRefreshToken();
        if (/^[A-Za-z0-9-_]*\.[A-Za-z0-9-_]*\.[A-Za-z0-9-_]*$/.test(accessToken)) {
            fetch("GET", "auth/init", { token: accessToken }, handleAccessTokenResponse);
        }
        else
            callback(400);
        function handleAccessTokenResponse(status, data) {
            if (status === 200)
                callback(200, data);
            else
                fetch("POST", "auth/refresh", { token: refreshToken }, handleRefreshTokenResponse);
        }
        ;
        function handleRefreshTokenResponse(status, data) {
            callback(status, data);
        }
        ;
    };
    /** Trata de iniciar sesión con los credenciales proporcionados.*/
    Session.getByCredentials = function (usernameOrEmail, password, callback) {
        fetch("POST", "auth/login", {
            body: JSON.stringify({
                usernameOrEmail: usernameOrEmail.trim(),
                password: password.trim(),
            })
        }, callback);
    };
    /**Establece los valores obtenidos de una solicitud de inicio de sesión exitosa.*/
    Session.setSession = function (session) {
        if (session.accessToken)
            document.cookie = "accessToken=".concat(session.accessToken, "; max-age=1209600; path=/; Secure");
        if (session.refreshToken)
            document.cookie = "refreshToken=".concat(session.refreshToken, "; max-age=1209600; path=/; Secure");
        if (session.username)
            sessionStorage.setItem("username", session.username);
        if (session.role)
            sessionStorage.setItem("role", session.role);
        if (session.active !== undefined)
            sessionStorage.setItem("actives", session.active);
        if (session.pasive !== undefined)
            sessionStorage.setItem("passives", session.pasive);
    };
    /** Forza la expiración de los tokens de sesión. Recarga la ubicación actual al finalizar.*/
    Session.close = function () {
        for (var _i = 0, _a = document.cookie.split(";"); _i < _a.length; _i++) {
            var cookie = _a[_i];
            document.cookie = cookie + "=; Secure; path=/; expires=" + new Date(0).toUTCString();
        }
        localStorage.removeItem("avatar");
        window.location.reload(); //SHOULD STORE OLD TOKENS FOR SECURITY
    };
    //## GETTERS ##//
    Session.getAccessToken = function () {
        var cookieArray = decodeURIComponent(document.cookie).split("; ");
        return cookieArray[0].substring("accessToken=".length);
    };
    Session.getRefreshToken = function () {
        var cookieArray = decodeURIComponent(document.cookie).split("; ");
        return cookieArray[0].substring("refreshToken=".length);
    };
    return Session;
}());
export default Session;
