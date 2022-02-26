var Group = /** @class */ (function () {
    function Group() {
    }
    /**
     * Recupera un array de grupos a nombre de la cuenta solicitante.
     * Un id puede ser proporcionado en el URL para filtrar la respuesta.
     * @param callback La función que procesará la respuesta.
     */
    Group.getArray = function (callback) {
        callback(200, JSON.stringify([
            {
                id: 1,
                value: "group A",
                tooltip: "4 integrantes"
            },
            {
                id: 2,
                value: "socio B",
                tooltip: "20 integrantes"
            },
            {
                id: 3,
                value: "socio C",
                tooltip: "2 integrantes"
            }
        ]));
    };
    return Group;
}());
export default Group;
