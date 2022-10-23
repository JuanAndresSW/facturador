import ajax from 'ports/ajax';
import Response from 'models/Response';
import jsonToDocumentHistory from "../adapters/jsonToDocumentHistory";
import documentClassCodeToDocumentName from "../utilities/documentClassCodeToDocumentName";
import { documentClassCode } from '../models/operation';
import {IDTrader} from "utilities/constants";


type documentHistoryFilters = {
    IDBranch?:          number,
    IDPointOfSale?:     number,
    documentClassCode?: documentClassCode
}


/**Recupera el historial de documentos comerciales, aceptando varios filtros. */
export default async function getDocumentHistory(filters: documentHistoryFilters): Promise<Response> {

    const params = `?IDBranch=${filters.IDBranch}&IDPointOfSale=${filters.IDPointOfSale}&repository=${documentClassCodeToDocumentName(filters.documentClassCode)}`;
    const URL    = `operations/${IDTrader}${params}`;

    const response = await ajax('GET', URL, true);

    if (response.status === 200) return {...response, content: jsonToDocumentHistory(response.content)};
    return response;

}