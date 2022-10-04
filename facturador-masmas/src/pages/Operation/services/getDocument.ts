import ajax from 'ports/ajax';
import Response from 'models/Response';
import documentIdentifier from "../models/documentIdentifier";
import documentClassCodeToDocumentName from "../utilities/documentClassCodeToDocumentName";
import {IDTrader} from "utilities/constants";
import jsonToDocument from '../adapters/jsonToDocument';

/**Recupera un documento comercial utilizando un identificador de documento. */
export default async function getDocument(docID: documentIdentifier): Promise<Response> {

    const params = `?operationNumber=${docID.documentNumber}&type=${docID.documentType}&IDTrader=${IDTrader}&IDBranch=${docID.IDBranch}`;

    const response = await ajax('GET', 
    `operations/fulls/${documentClassCodeToDocumentName(docID.documentClassCode)}${params}`, true);

    if (response.status === 200) return {...response, content: (jsonToDocument(response.content, docID.documentClassCode))};
    return response;

}