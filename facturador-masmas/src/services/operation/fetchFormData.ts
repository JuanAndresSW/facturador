/**
* Recupera arrays de puntos de venta, socios y grupos del servidor.
* @param callback La función que procesará la respuesta. 
*/
export default function fetchFormData(callback: Function): void {
    callback(200, JSON.stringify({
        pointsOfSale: [
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
            {
                id: 3,
                name: "punto3",
                address: "calle3",
            },
            {
                id: 4,
                name: "punto4",
                address: "calle4",
            },
            {
                id: 6,
                name: "punto5",
                address: "calle5",
            },
            {
                id: 8,
                name: "punto6",
                address: "calle6",
            }
        ],
        partners: [
            {
                id: 1,
                name: "socio A",
                address: "ciudad A"
            },
            {
                id: 2,
                name: "socio B",
                address: "ciudad B"
            },
            {
                id: 3,
                name: "socio C",
                address: "ciudad C"
            }
        ],
        groups: [
            {
                id: 1,
                name: "group A",
                address: "ciudad A"
            },
            {
                id: 2,
                name: "socio B",
                address: "ciudad B"
            },
            {
                id: 3,
                name: "socio C",
                address: "ciudad C"
            }
        ]

    }))
}