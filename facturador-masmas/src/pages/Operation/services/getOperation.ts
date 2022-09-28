import ajax from 'ports/ajax';
import Response from 'models/Response';
import { documentClassCode } from '../models/operation';
import operationIdentifier from "../models/operationIdentifier";
import operationToJson from "../adapters/operationToJson";

export default async function getOperation(operationIdentifier: operationIdentifier): Promise<Response> {
    const response = await ajax('GET', `operation/send`, true);

    if (response.status === 201) return {...response, content: (JSON.parse(response.content))};
    return response;

}

function getOperationName(type: documentClassCode): string {
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