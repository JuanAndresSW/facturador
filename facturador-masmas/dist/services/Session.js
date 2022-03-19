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
        var handleAccessTokenResponse = function (status, data) {
            if (status === 200) {
                _this.setSession(JSON.parse(data));
                callback(200);
            }
            else
                fetch("POST", "auth/refresh", { token: refreshToken }, handleRefreshTokenResponse);
        };
        if (/^[A-Za-z0-9-_]*\.[A-Za-z0-9-_]*\.[A-Za-z0-9-_]*$/.test(accessToken)) {
            fetch("GET", "auth/init", { token: accessToken }, handleAccessTokenResponse);
        }
        else
            callback(400, "No existe un token con formato válido almacenado.");
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
        sessionStorage.setItem("username", session.username);
        sessionStorage.setItem("role", session.rol);
        sessionStorage.setItem("actives", session.active);
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
/**
 * username	"test1"
 * activos	0
 * pasivos	0
 * accessToken
 * refreshToken
 */
//TODO: Access-Token => accessToken; POST => GET.
//      activos => actives; pasivos => passives;
//      rol => role; 
/**{"Access-Token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuYW1ldyIsImlzcyI6Ii9hcGkvYXV0a
 * C9yZWZyZXNoIiwiZXhwIjoxNjQ3NTQ3OTk3LCJpYXQiOjE2NDc1MzM1OTcsInJvbCI6Ik1BSU4ifQ.fxacPfsQKmfemidJR
 * Gh9TpfvdtsG1_BSkFlqYygW9y4",
 *
 * "Refresh-Token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuYW1ldyIsImlzcyI6Ii9hcGkvYXV0a
 * C9yZWZyZXNoIiwiZXhwIjoxNjQ3ODA3MTk3LCJpYXQiOjE2NDc1MzM1OTd9.L6KwVZgQSiIPzW3e0aUQtwPeIaSmzJC9Fvh
 * j4HixcHI"} */ 
