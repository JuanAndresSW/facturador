import ajax from 'ports/ajax';
import getToken from 'services/getToken';
import {IDTrader} from 'utilities/constants';

/**
 * Registra un nuevo punto de venta.
 * @param IDBranch - El ID de la sucursal a la cual pertenece el punto.
 * @param callback - La función que procesará la respuesta.
 */
export default function postPointOfSale(IDBranch:number, callback: Function): void {
    ajax('POST', `pointsofsale/branch/${IDBranch}/trader/${IDTrader}`,
    {token: getToken('access')}, handle);

    function handle(status: number, content: string) {
        if (status === 201) callback(true, 'Se ha creado el punto de venta');
        else callback(false, content);
    }
}