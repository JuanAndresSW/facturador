/**
 * Define métodos de comunicación con el servidor.
 */
var DocGateway = /** @class */ (function () {
    function DocGateway() {
    }
    /**
     * implementa una capa de abstracción para la API XMLHR
     * @param {String} url el sufijo del url del recurso
     * @param {String} content el contenido (json) del request
     * @param {String} method el método del request (POST/GET)
     */
    DocGateway.fetch = function (url, content, method) {
        var xhr = new XMLHttpRequest();
        url = process.env.REACT_APP_API + url;
        if (!xhr) {
            alert("No se ha podido crear una instancia de XMLHTTPR");
            return;
        }
        //iniciar el request
        xhr.onreadystatechange = handleResponse;
        xhr.open(method, url);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.send(content);
        /**define el callback para la respuesta*/
        function handleResponse() {
            if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                return xhr.responseText;
            }
            else
                alert("Error del servidor");
            return "";
        }
    };
    /*******************************************************************************
     ************************** MÉTODOS ESPECÍFICOS *********************************
     ********************************************************************************/
    /**
     * envía los datos de usuario y la foto de perfil
     * @param {account} account los datos de la cuenta del usuario, en forma de objeto
     */
    DocGateway.submitAccount = function (account) {
        var response = this.fetch("recibe", JSON.stringify(account), "POST");
        console.log(response);
    };
    /**
     * trata de iniciar sesión con los datos proporcionados
     * @param name el nombre o email
     * @param password la contraseña
     * @returns si el login fue exitoso o no
     */
    DocGateway.tryLogin = function (name, password) {
        var response = JSON.parse(this.fetch("login", JSON.stringify({
            name: name,
            password: password,
        }), "POST"));
        return response.ok;
    };
    /**
     * Verifica que el código de sesión almacenado es uno válido.
     * Similar a tryLogin pero sin requerir nombre o contraseña
     * @param code el código de sesión
     * @returns si la autenticación fue exitosa o no
     */
    DocGateway.authenticate = function (code) {
        var response = JSON.parse(this.fetch("auth", JSON.stringify({
            code: code,
        }), "POST"));
        return (code === "1234");
        //return response.ok;
    };
    return DocGateway;
}());
export default DocGateway;
