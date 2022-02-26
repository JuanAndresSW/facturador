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
        var token = this.getAccessToken();
        if (/^[A-Za-z0-9-_]*\.[A-Za-z0-9-_]*\.[A-Za-z0-9-_]*$/.test(token)) {
            callback(200, token);
            return;
        }
        else
            callback(400);
        fetch("get", "/auth", { token: token }, callback);
    };
    /**
    * Trata de iniciar sesión con los credenciales proporcionados.
    * @param {string}   [usernameOrEmail] - Nombre o email.
    * @param {string}   [password]        - Contraseña.
    * @param {Function} [callback]        - Función de manejo de respuesta.
    */
    Session.getByCredentials = function (name, password, callback) {
        fetch("post", "auth/login", {
            body: JSON.stringify({
                usernameOrEmail: name.trim(),
                password: password.trim(),
            })
        }, callback);
    };
    /**
     * Establece los valores obtenidos de una solicitud de login exitosa.
     * @param {string} [session.accessToken] - El JWT usado para autenticar peticiones.
     * @param {string} [session.refreshToken] - El JWT usado para autenticar una solicitud de renovación de token de accesso.
     */
    Session.setSession = function (_a) {
        var accessToken = _a.accessToken, refreshToken = _a.refreshToken;
        if (accessToken === undefined || refreshToken === undefined)
            return;
        document.cookie = "accessToken=".concat(accessToken, "; max-age=1209600; path=/; Secure");
        document.cookie = "refreshToken=".concat(refreshToken, "; max-age=1209600; path=/; Secure");
    };
    /** Forza la expiración de los tokens de sesión. Recarga la ubicación actual al finalizar.*/
    Session.close = function () {
        for (var _i = 0, _a = document.cookie.split(";"); _i < _a.length; _i++) {
            var cookie = _a[_i];
            document.cookie = cookie + "=; Secure; expires=" + new Date(0).toUTCString();
        }
        window.location.reload();
    };
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
