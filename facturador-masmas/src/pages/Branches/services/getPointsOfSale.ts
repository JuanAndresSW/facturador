import ajax from 'ports/ajax';
import getToken from 'services/getToken';

/**
 * Obtiene una lista de puntos de venta.
 * @param IDBranch - El ID de la sucursal a la cual pertenece el punto.
 * @param callback - La función que procesará la respuesta.
 */
export default function getPointsOfSale(IDBranch:number, page:number, callback: Function): void {
    ajax('GET', `pointsofsale/branch/${IDBranch}?index=${page}&size=12&sort=pointOfSaleNumber&order=asc`,
    {token: getToken('access')}, handle);

    function handle(status: number, pointsPages: string) {
        if (status === 200) callback(true, JSON.parse(pointsPages));
        else callback(false, "No se ha podido recuperar los puntos de venta");
    }
}