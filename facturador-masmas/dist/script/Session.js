import Gateway from "../script/Gateway";
/**
 * métodos para controlar la sesión
 */
var Session = /** @class */ (function () {
    function Session() {
    }
    /**
     * Intenta iniciar sesión
     * @param user el nombre de usuario o email
     * @param password la contraseña proveída
     * @param callback la función que procesará la respuesta
     * @returns si el inicio fue exitoso o no
     */
    Session.tryStart = function (user, password, callback) {
        Gateway.tryLogin(user, password, callback);
    };
    /**
     * establece los valores obtenidos de un login exitoso
     * @param {String} token: el token de la sesión
     * @param {String} name: el nombre de usuario
     * @param {String} passive: la cantidad de patrimonio pasivo
     * @param {String} active: la cantidad de patrimonio activo
     */
    Session.setSession = function (token, name, active, passive) {
        document.cookie = "session=".concat(token, "; max-age=1209600; path=/; Secure");
        sessionStorage.setItem("username", name);
        sessionStorage.setItem("active", active.toString());
        sessionStorage.setItem("passive", passive.toString());
    };
    /**
     * Obtiene el token de sesión de las cookies.
     * @returns {String} el código de sesión, si existe.
     */
    Session.getSession = function () {
        var cookieArray = decodeURIComponent(document.cookie).split("; ");
        return cookieArray[0].substring("session=".length);
    };
    /**
     * Evalúa si existe un token de sesión válido en las cookies.
     */
    Session.isAuthenticated = function () {
        return Gateway.authenticate(this.getSession());
    };
    /**
     * Intermediario para recuperar el objeto `key` de sessionStorage.
     */
    Session.get = function (key) {
        return (sessionStorage.getItem(key) !== null) ?
            sessionStorage.getItem(key) : "?";
    };
    /**
     * borra la cookie de sesión; la contraparte en la base de datos se debe eliminar tiempo después
     */
    Session.close = function () {
        var cookies = document.cookie.split(";");
        for (var _i = 0, cookies_1 = cookies; _i < cookies_1.length; _i++) {
            var cookie = cookies_1[_i];
            document.cookie = cookie + "=;expires=" + new Date(0).toUTCString();
        }
        sessionStorage.clear();
    };
    /**
     * renovar el tiempo de expiración de la sesión
     */
    Session.renew = function () {
        document.cookie = "session=".concat(this.getSession(), "; max-age=1209600; path=/; Secure");
    };
    /**
     * renovar datos de la sesión
     */
    Session.refresh = function () {
        /* sessionStorage.setItem("active", this.tempUser.active);
        sessionStorage.setItem("passive", this.tempUser.passive);
        sessionStorage.setItem("username", this.tempUser.name); */
    };
    return Session;
}());
export default Session;
