import ajax from 'ports/ajax';
import { IDTrader } from 'utilities/constants';
import getToken from 'services/getToken';

/**
 * Recupera un array de sucursales con sus puntos de venta a nombre de la cuenta solicitante.
 * @param callback La función que procesará la respuesta. 
 */
export default function getListOfBranchesAndPoints(callback: Function): void {
    ajax("GET", `branches/traders/${IDTrader}`, {token: getToken('access')}, handleResponse);

    function handleResponse(httpStatus: number, content: string): Function {
        if (httpStatus === 400) return callback(true, []);
        if (httpStatus === 200) return callback(true, JSON.parse(content));
        return callback(false);
    }
}