import Gateway from '../script/Gateway';
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
     * @returns si el inicio fue exitoso o no
     */
    Session.tryStart = function (user, password) {
        if (Gateway.tryLogin(user, password)) {
            return true;
        }
        return false;
    };
    /**
     * establece los valores obtenidos de un login exitoso
     * @param {String} code: el código de la sesión
     * @param {String} name: el nombre de usuario
     * @param {String} passive: la cantidad de patrimonio pasivo
     * @param {String} active: la cantidad de patrimonio activo
     */
    Session.setSession = function (code, name, passive, active) {
        document.cookie = "session=".concat(this.tempUser.session, "; max-age=1209600; path=/; Secure");
        sessionStorage.setItem("active", this.tempUser.active);
        sessionStorage.setItem("passive", this.tempUser.passive);
        sessionStorage.setItem("username", this.tempUser.name);
    };
    /**
     * obtiene el código de sesión de las cookies
     * @returns {String} el código de sesión, si existe
     */
    Session.getSession = function () {
        var cookieArray = decodeURIComponent(document.cookie).split("; ");
        try {
            return cookieArray[0].substring("session=".length);
        }
        catch (NullPointerException) {
            return "?";
        }
    };
    /**
     * @returns si existe un código de sesión válido en las cookies
     */
    Session.isAuthenticated = function () {
        return Gateway.authenticate(this.getSession());
    };
    /**
     * intermediario para recuperar el objeto `key` de sessionStorage
     */
    Session.get = function (key) {
        try {
            return sessionStorage.getItem(key);
        }
        catch (NullPointerException) {
            return "?";
        }
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
        sessionStorage.removeItem("username");
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
        sessionStorage.setItem("active", this.tempUser.active);
        sessionStorage.setItem("passive", this.tempUser.passive);
        sessionStorage.setItem("username", this.tempUser.name);
    };
    //objeto de usuario a comparar, imitando a un registro en la base de datos
    Session.tempUser = {
        name: "niño",
        password: "software",
        session: "1234",
        active: "1.55",
        passive: "2",
    };
    return Session;
}());
export default Session;
