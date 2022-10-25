import ajax from 'ports/ajax';
import Response from 'models/Response';
import jsonToDocumentHistory from "../adapters/jsonToDocumentHistory";
import documentClassCodeToDocumentName from "../utilities/conversions/documentClassCodeToDocumentName";
import { documentClassCode } from '../models/operation';
import {IDTrader} from "utilities/constants";


type documentHistoryFilters = {
    IDBranch?:          number,
    pointOfSaleNumber?: number,
    documentClassCode?: documentClassCode
}


/**Recupera el historial de documentos comerciales, aceptando varios filtros. */
export default async function getDocumentHistory(filters: documentHistoryFilters): Promise<Response> {

    let params = Number.isInteger(filters.IDBranch)? `IDBranch=${filters.IDBranch}&` : '';
    params +=    Number.isInteger(filters.pointOfSaleNumber)? `pointOfSaleNumber=${filters.pointOfSaleNumber}&` : '';
    params +=    filters.documentClassCode? `repository=${documentClassCodeToDocumentName(filters.documentClassCode)}` : '';

    const URL    = `operations/traders/${IDTrader}?${params}`;

    const response = await ajax('GET', URL, true);

    if (response.status === 200) return {...response, content: jsonToDocumentHistory(response.content)};
    return response;

}