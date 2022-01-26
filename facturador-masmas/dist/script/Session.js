/**
 * métodos para controlar la sesión
 */
var Session = /** @class */ (function () {
    function Session() {
    }
    Session.authenticate = function (user, password) {
        //enviar el objeto al servidor, que devuelve una página con un objeto cookie o un mensaje de error
        //por ahora, se establece la cookie localmente (1209600=14d)
        if (user === this.tempUser.name && password === this.tempUser.password) {
            return true;
        }
        return false;
    };
    //establecer la sesión con el código de sesión recibido
    Session.open = function (session) {
        document.cookie = "username=".concat(session, "; max-age=1209600; path=/; Secure");
    };
    //verifica si existe un código de sesión válido en las cookies
    Session.isAuthenticated = function () {
        if (this.getUsername() === "niño") {
            return true;
        }
        return false;
    };
    //recupera el valor de nombre de usuario de las cookies
    Session.getUsername = function () {
        var cookieArray = decodeURIComponent(document.cookie).split("; ");
        try {
            return cookieArray[0].substring("username=".length);
        }
        catch (NullPointerException) {
            return "";
        }
    };
    //borra la cookie de sesión; la contraparte en la base de datos se debe eliminar tiempo después
    Session.close = function () {
        var cookies = document.cookie.split(";");
        for (var _i = 0, cookies_1 = cookies; _i < cookies_1.length; _i++) {
            var cookie = cookies_1[_i];
            document.cookie = cookie + "=;expires=" + new Date(0).toUTCString();
        }
    };
    //renovar el tiempo de expiración de la sesión local y en la BD
    Session.renew = function () {
        document.cookie = "username=".concat(this.getUsername(), "; max-age=1209600; path=/; Secure");
    };
    //objeto de usuario a comparar, imitando a un registro en la base de datos
    Session.tempUser = {
        name: "niño",
        password: "software",
    };
    return Session;
}());
export default Session;
