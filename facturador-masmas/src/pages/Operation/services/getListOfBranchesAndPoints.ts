import ajax from 'ports/ajax';
import { IDTrader } from 'utilities/constants';
import Response from 'models/Response';
import jsonToListOfBranchesAndPoints from "../adapters/jsonToListOfBranchesAndPoints";

/**
 * Recupera un array de sucursales, cada uno con sus puntos de venta a nombre de la cuenta solicitante.
 */
export default async function getListOfBranchesAndPoints(usePointOfSaleNumberInsteadOfID=false): Promise<Response> {
    const response = await ajax("GET", `branches/traders/${IDTrader}/summary`, true);

    if (response.status === 400) return {...response, content: []};
    if (response.status === 200) return {...response, content: jsonToListOfBranchesAndPoints(response.content, usePointOfSaleNumberInsteadOfID)};
    return response;
    
}