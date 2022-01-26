/**
 * solicitar datos necesarios para mostrar el formulario de generacion de documentos;
 * guardar los datos recolectados del usuario en la sesión para enviar al servidor;
 */
var DocData = /** @class */ (function () {
    function DocData() {
    }
    /**MÉTODOS DE RECUPERACIÓN DEL SERVIDOR********************************************/
    //recupera arrays de puntos de venta, socios y grupos del servidor. Almacena en la sesión
    DocData.fetchFormData = function () {
        if (sessionStorage.getItem("points-of-sale") === null) {
            sessionStorage.setItem("points-of-sale", JSON.stringify([
                {
                    id: 2,
                    name: "punto1",
                    address: "calle1",
                },
                {
                    id: 1,
                    name: "punto2",
                    address: "calle2",
                },
            ]));
            sessionStorage.setItem("third-parties", JSON.stringify([
                {
                    id: 2,
                    name: "empresa",
                    vatType: "monotributista",
                },
                {
                    id: 1,
                    name: "persona",
                    vatType: "consumidor final",
                },
            ]));
            sessionStorage.setItem("groups", JSON.stringify([
                {
                    id: 2,
                    name: "grupo A",
                    members: 22,
                },
                {
                    id: 1,
                    name: "grupo B",
                    members: 12,
                },
            ]));
            //determina si los tipos de documentos A Y B deben permitirse
            sessionStorage.setItem("allowAB", "1");
        }
        return;
    };
    /*MÉTODOS DE RECUPERACIÓN LOCAL***********************************************/
    //recuperar de la sesión un array para construir los elementos <option> de <select>
    DocData.getPointsOfSale = function () {
        return JSON.parse(sessionStorage.getItem("points-of-sale"));
    };
    DocData.getThirdParties = function () {
        return JSON.parse(sessionStorage.getItem("third-parties"));
    };
    DocData.getGroups = function () {
        return JSON.parse(sessionStorage.getItem("groups"));
    };
    //verifica si el string corresponde con el nombre de cualquier elemento dentro de `item`;
    //devuelve el índice de la primera coincidencia encontrada;
    //se necesita para saber si un término de búsqueda debe ser aceptado o ignorado
    DocData.getIndexIfExists = function (searchTerm, item) {
        var matches = function (element) {
            return element.name.toLowerCase() === searchTerm.toLowerCase().trim();
        };
        return JSON.parse(sessionStorage.getItem(item)).findIndex(matches);
    };
    /**MÉTODOS DE ALMACENAMIENTO DE DATOS****************************************************/
    DocData.setPointOfSale = function (index) {
        var data = JSON.parse(sessionStorage.getItem("doc-data"));
        data.pointOfSale = this.getPointsOfSale()[index].id;
        sessionStorage.setItem("doc-data", JSON.stringify(data));
    };
    DocData.setThirdParty = function (index) {
        console.log("huh");
        var data = JSON.parse(sessionStorage.getItem("doc-data"));
        data.thirdParty = this.getThirdParties()[index].id;
        sessionStorage.setItem("doc-data", JSON.stringify(data));
    };
    DocData.setGroup = function (index) {
        if (index !== -1) {
            var data = JSON.parse(sessionStorage.getItem("doc-data"));
            data.group = this.getGroups()[index].id;
            sessionStorage.setItem("doc-data", JSON.stringify(data));
        }
    };
    //en la primera renderización, define los valores iniciales de los campos en la sesión
    DocData.initializeValues = function () {
        if (sessionStorage.getItem("doc-data") === null)
            sessionStorage.setItem("doc-data", JSON.stringify({
                pointOfSale: this.getPointsOfSale()[0].id,
                thirdParty: this.getThirdParties()[0].id,
            }));
    };
    //verificar si los documentos A y B están permitidos
    DocData.allowAB = function () {
        return !!parseInt(sessionStorage.getItem("allowAB"));
    };
    return DocData;
}());
export default DocData;
