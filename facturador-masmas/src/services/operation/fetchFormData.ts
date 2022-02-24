/**
* Recupera arrays de puntos de venta, socios y grupos del servidor.
* @param callback La función que procesará la respuesta. 
*/
export default function fetchFormData(callback: Function): void {
    callback(200, JSON.stringify({
        pointsOfSale: [
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
        ],
        partners: [
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
        ],
        groups: [
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
        ],
        root: true

    }))
}