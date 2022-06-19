import ajax from 'ports/ajax';

/**
 * Recupera un array de grupos a nombre de la cuenta solicitante.
 * @param callback La función que procesará la respuesta. 
 */
export default function retrieveGroups(callback: Function): void {
    callback(200, JSON.stringify(
        [
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
        ]
    ));
}