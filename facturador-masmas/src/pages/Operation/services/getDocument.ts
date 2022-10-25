import ajax from 'ports/ajax';
import Response from 'models/Response';
import documentIdentifier from "../models/documentIdentifier";
import documentClassCodeToDocumentName from "../utilities/conversions/documentClassCodeToDocumentName";
import jsonToDocument from '../adapters/jsonToDocument';

/**Recupera un documento comercial utilizando un identificador de documento. */
export default async function getDocument(docID: documentIdentifier): Promise<Response> {

    const subrepository = "FaNdNc".includes(docID.documentClassCode)? "/fulls" : '';
    const params        = `?operationId=${docID.IDOperation}`;
    const URL           = `operations${subrepository}/${documentClassCodeToDocumentName(docID.documentClassCode)}${params}`;

    const response = await ajax('GET', URL, true);

    if (response.status === 200) return {...response, content: await jsonToDocument(response.content, docID.documentClassCode)};
    return response;

}