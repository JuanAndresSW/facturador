import fetch from 'api/fetch';
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
        var _this = this;
        var accessToken = this.getAccessToken();
        var refreshToken = this.getRefreshToken();
        var handleRefreshTokenResponse = function (status, data) {
            if (status === 200) {
                _this.setSession(JSON.parse(data));
                callback(200);
            }
            callback(status);
        };
        var handleAccessTokenResponse = function (status) {
            if (status === 200)
                callback(200);
            else
                fetch("POST", "auth/refresh", { token: refreshToken }, handleRefreshTokenResponse); //Volver a intentar con el otro token.
        };
        if (/^[A-Za-z0-9-_]*\.[A-Za-z0-9-_]*\.[A-Za-z0-9-_]*$/.test(accessToken)) {
            fetch("POST", "auth/init", { token: accessToken }, handleAccessTokenResponse); //should be "HEAD"
        }
        else
            callback(400);
    };
    /**
    * Trata de iniciar sesión con los credenciales proporcionados.
    * @param {string}   [usernameOrEmail] - Nombre o email.
    * @param {string}   [password]        - Contraseña.
    * @param {Function} [callback]        - Función de manejo de respuesta.
    */
    Session.getByCredentials = function (usernameOrEmail, password, callback) {
        fetch("POST", "auth/login", {
            body: JSON.stringify({
                usernameOrEmail: usernameOrEmail.trim(),
                password: password.trim(),
            })
        }, callback);
    };
    /**
     * Establece los valores obtenidos de una solicitud de login exitosa.
     * @param {string} [session.accessToken] - El JWT usado para autenticar peticiones.
     * @param {string} [session.refreshToken] - El JWT usado para autenticar una solicitud de renovación de token de acceso.
     */
    Session.setSession = function (session) {
        if (session.accessToken === undefined || session.refreshToken === undefined)
            return;
        document.cookie = "accessToken=".concat(session.accessToken, "; max-age=1209600; path=/; Secure");
        document.cookie = "refreshToken=".concat(session.refreshToken, "; max-age=1209600; path=/; Secure");
    };
    /** Forza la expiración de los tokens de sesión. Recarga la ubicación actual al finalizar.*/
    Session.close = function () {
        for (var _i = 0, _a = document.cookie.split(";"); _i < _a.length; _i++) {
            var cookie = _a[_i];
            document.cookie = cookie + "=; Secure; expires=" + new Date(0).toUTCString();
        }
        window.location.reload();
    };
    //GETTERS
    /** Recupera el token de acceso del array de cookies. */
    Session.getAccessToken = function () {
        var cookieArray = decodeURIComponent(document.cookie).split("; ");
        return cookieArray[0].substring("accessToken=".length);
    };
    /** Recupera el token de refrescamiento del array de cookies. */
    Session.getRefreshToken = function () {
        var cookieArray = decodeURIComponent(document.cookie).split("; ");
        return cookieArray[0].substring("refreshToken=".length);
    };
    return Session;
}());
export default Session;
