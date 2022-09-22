import ajax from 'ports/ajax';
import getToken from 'services/getToken';
import operation, { operationCode } from '../models/operation';
import operationIdentifier from "../models/operationIdentifier";
import operationToJson from "../adapters/operationToJson";

export default function postOperation(operation: operation, type: operationCode, callback: Function) {
    ajax('POST', `operation/${getOperationName(type)}/send`,
    {body: operationToJson(operation, type), 
    token: getToken('access')}, 
    handleResponse);

    function handleResponse(httpStatus: number, content: string) {
        if (httpStatus === 201) callback(JSON.parse(content).operationNumber);
        else callback(false, content);
    }
}

function getOperationName(type: operationCode): string {
    switch (type) {
        case "Oc":  return "purchase-order";
        case "Rm":  return "remittance";
        case "Fa":  return "invoice ";
        case "Nd":  return "debit-note";
        case "Nc":  return "credit-note";
        case "Rx":  return "receipt-x";
        case "Rs":  return "receipt";
        case "Pa":  return "promissory-note";
        case "Ch":  return "check";
        default: return "Unexpected 'type' parameter: "+type;
    }
}