import ajax from 'interceptors/ajax';


/**
 * Recupera un array de socios a nombre de la cuenta solicitante.
 * @param callback La función que procesará la respuesta. 
 */
export default function retrievePartners(callback: Function): void {
    callback(200, JSON.stringify(
        [
            {
                id: 1,
                tooltip: "socio A",
                value: "ciudad A"
            },                {
                id: 2,
                tooltip: "socio B",
                value: "ciudad B"
            },                {
                id: 3,
                tooltip: "socio C",
                value: "ciudad C"
            }
        ]

    ))
}
