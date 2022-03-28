import ajax from 'interceptors/ajax';
//import pointOfSale from 'models/pointOfSale';


/**
 * Recupera un array de puntos de venta a nombre de la cuenta solicitante.
 * @param callback La función que procesará la respuesta. 
 */
export default function retrievePointsOfSale(callback: Function): void {
    callback(200, JSON.stringify(
        [
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
        ]

    ));
}