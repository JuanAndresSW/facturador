import ajax from 'ports/ajax';
import { IDTrader } from 'utilities/constants';
import Response from 'models/Response';

/**
 * Recupera un array de sucursales con sus puntos de venta a nombre de la cuenta solicitante.
 */
export default async function getListOfBranchesAndPoints(): Promise<Response> {
    const response = await ajax("GET", `branches/traders/${IDTrader}`, true);

    if (response.status === 400) return {...response, content: []};
    if (response.status === 200) return {...response, content: JSON.parse(response.content)};
    return response;
    
}