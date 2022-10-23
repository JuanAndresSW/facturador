import Response from 'models/Response';
import ajax from 'ports/ajax';
import getToken from 'services/getToken';
import {IDTrader} from 'utilities/constants';

/**
 * Registra un nuevo punto de venta.
 * @param IDBranch - El ID de la sucursal a la cual pertenece el punto.
 */
export default async function postPointOfSale(IDBranch:number): Promise<Response> {
    return await ajax('POST', `pointsofsale/branches/${IDBranch}/traders/${IDTrader}`, true);
}