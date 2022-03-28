var PointOfSale = /** @class */ (function () {
    function PointOfSale() {
    }
    PointOfSale.create = function (pointOfSale, callback) {
    };
    /**
     * Recupera un array de puntos de venta a nombre de la cuenta solicitante.
     * Un id puede ser proporcionado en el URL para filtra
     * @param callback La función que procesará la respuesta.
     */
    PointOfSale.retrieve = function (callback) {
        callback(200, JSON.stringify([
            {
                id: 2,
                tooltip: "punto1",
                value: "calle1",
            },
            {
                id: 1,
                tooltip: "punto2",
                value: "calle2",
            },
            {
                id: 3,
                tooltip: "punto3",
                value: "calle3",
            },
            {
                id: 4,
                tooltip: "punto4",
                value: "calle4",
            },
            {
                id: 6,
                tooltip: "punto5",
                value: "calle5",
            },
            {
                id: 8,
                tooltip: "punto6",
                value: "calle6",
            }
        ]));
    };
    return PointOfSale;
}());
export default PointOfSale;
