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
