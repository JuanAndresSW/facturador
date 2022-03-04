import fetch from 'api/fetch';

export type pointOfSale = {
    name: string;
    address: string;
    locality: string;
    postalCode: string;
    email: string;
    phone: string;
    website: string;
    color: string;
    logo: File;
};

export default class PointOfSale {
    /**
     * Recupera un array de puntos de venta a nombre de la cuenta solicitante.
     * Un id puede ser proporcionado en el URL para filtra
     * @param callback La función que procesará la respuesta. 
     */
    public static getArray(callback: Function): void {
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

        ))
    }
}