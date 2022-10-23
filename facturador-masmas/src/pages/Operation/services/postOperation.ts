import ajax from 'ports/ajax';
import Response from 'models/Response';
import operation from '../models/operation';
import operationToJson from "../adapters/operationToJson";
import jsonToDocumentIdentifier from "../adapters/jsonToDocumentIdentifier";
import documentClassCodeToDocumentName from "../utilities/documentClassCodeToDocumentName";

export default async function postOperation(operation: operation): Promise<Response> {

    const subrepository = "FaNdNc".includes(operation.documentClassCode)? "/fulls" : '';
    const URL = `operations${subrepository}/${documentClassCodeToDocumentName(operation.documentClassCode)}`

    const response = await ajax('POST', URL, true, operationToJson(operation, operation.documentClassCode, true))

    if (response.status === 201)
    return {...response, content: (jsonToDocumentIdentifier(response.content, operation.documentClassCode))};
    else return response;

}