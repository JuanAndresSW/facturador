import Format from "./Format";
/**
 * define métodos de comunicación con el servidor.
 */
var DocGateway = /** @class */ (function () {
    function DocGateway() {
    }
    //envía un json con los datos de usuario y blobs con el logo y la foto de perfil
    DocGateway.submitAccount = function (account, url) {
        //definir datos a enviar
        var data = new FormData();
        data.append("account", Format.account(account));
        if (account.user.avatar !== null) {
            data.append("photo", account.user.avatar);
        }
        if (account.pointOfSale.logo !== null) {
            data.append("logo", account.pointOfSale.logo);
        }
        //enviar y esperar por el código de sesión o el error
        fetch(url, {
            method: "POST",
            body: data,
        })
            .then(function (response) { return response.json(); })
            .then(function (data) {
            console.log("Success:", data);
        })
            .catch(function (error) {
            console.error("Error:", error);
        });
    };
    return DocGateway;
}());
export default DocGateway;
