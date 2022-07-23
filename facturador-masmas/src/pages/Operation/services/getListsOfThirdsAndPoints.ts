//import ajax from 'ports/ajax';

/**
 * Recupera un array de sucursales con sus puntos de venta a nombre de la cuenta solicitante.
 * @param callback La función que procesará la respuesta. 
 */
export default function getListsOfThirdsAndPoints(callback: Function): void {
    callback(true, 
        [
            {
            locality: "Iguazu",
            street: "Streetx",
            addressNumber: 12,
            name: "Name",
            IDBranch: 66,
            pointsOfSale: [
                {
                    pointOfSaleID: 2,
                    pointOfSaleNumber: 4,
                },
                {
                    pointOfSaleID: 3,
                    pointOfSaleNumber: 6,
                },
                {
                    pointOfSaleID: 4,
                    pointOfSaleNumber: 8,
                },
            ]
            },

            {
                locality: "Posadas",
                street: "StreetY",
                addressNumber: 15,
                name: "Name2",
                IDBranch: 65,
                pointsOfSale: [
                    {
                        pointOfSaleID: 5,
                        pointOfSaleNumber: 10,
                    },
                    {
                        pointOfSaleID: 6,
                        pointOfSaleNumber: 12,
                    },
                    {
                        pointOfSaleID: 7,
                        pointOfSaleNumber: 14,
                    },
                ]
            },

            
        ]
        

    );
}