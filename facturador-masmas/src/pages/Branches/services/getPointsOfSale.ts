import Response from 'models/Response';
import ajax from 'ports/ajax';

/**
 * Obtiene una lista de puntos de venta.
 * @param IDBranch - El ID de la sucursal a la cual pertenece el punto.
 * @param callback - La función que procesará la respuesta.
 */
export default async function getPointsOfSale(IDBranch:number, page:number): Promise<Response> {
    const response = await ajax('GET', `pointsofsale/branch/${IDBranch}?index=${page}&size=12&sort=pointOfSaleNumber&order=asc`, true);

    if (response.status === 200) return {...response, content: JSON.parse(response.content)};
    return {...response, message: "No se ha podido recuperar los puntos de venta"}

}