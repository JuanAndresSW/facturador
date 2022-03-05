var Partner = /** @class */ (function () {
    function Partner() {
    }
    /**
     * Recupera un array de socios a nombre de la cuenta solicitante.
     * Un id puede ser proporcionado en el URL para filtrar la respuesta.
     * @param callback La función que procesará la respuesta.
     */
    Partner.getArray = function (callback) {
        callback(200, JSON.stringify([
            {
                id: 1,
                tooltip: "socio A",
                value: "ciudad A"
            },
            {
                id: 2,
                tooltip: "socio B",
                value: "ciudad B"
            },
            {
                id: 3,
                tooltip: "socio C",
                value: "ciudad C"
            }
        ]));
    };
    return Partner;
}());
export default Partner;
