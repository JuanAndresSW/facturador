/**
* Controlador local de la sesión de usuario.
* No se comunica directa ni indirectamente con el servidor.
*/
var Session = /** @class */ (function () {
    function Session() {
    }
    /**
     * Establece los valores obtenidos de un login exitoso.
     * @property `token` El token de la sesión.
     * @property `name` El nombre de usuario.
     * @property `passive` La cantidad de patrimonio pasivo.
     * @property `active` La cantidad de patrimonio activo.
     */
    Session.setSession = function (_a) {
        var token = _a.token, name = _a.name, active = _a.active, passive = _a.passive;
        document.cookie = "session=".concat(token, "; max-age=1209600; path=/; Secure");
        sessionStorage.setItem("username", name);
        sessionStorage.setItem("active", active.toString());
        sessionStorage.setItem("passive", passive.toString());
    };
    /** Evalúa si existe una sesión iniciada sin comprobar la legitimidad del token.*/
    Session.isAuthenticated = function () {
        var cookieArray = decodeURIComponent(document.cookie).split("; ");
        var token = cookieArray[0].substring("session=".length);
        return /^[A-Za-z0-9-_]*\.[A-Za-z0-9-_]*\.[A-Za-z0-9-_]*$/.test(token);
    };
    /** Borra la sesión.*/
    Session.close = function () {
        var cookies = document.cookie.split(";");
        for (var _i = 0, cookies_1 = cookies; _i < cookies_1.length; _i++) {
            var cookie = cookies_1[_i];
            document.cookie = cookie + "=; Secure; expires=" + new Date(0).toUTCString();
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
