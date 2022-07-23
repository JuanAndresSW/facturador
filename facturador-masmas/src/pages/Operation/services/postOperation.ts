import ajax from 'ports/ajax';
import getToken from 'services/getToken';
import operation from '../models/operation';

export default function postOperation(operation: operation, type: string, toSend: boolean, callback: Function) {
    ajax('POST', `operation/${getOperationName(type)}/${toSend?'send':'receive'}`,
    {body: JSON.stringify(operation), token: getToken('access')}, handleResponse);


    function handleResponse(httpStatus: number, content: string) {
        if (httpStatus === 201) callback(true);
        else callback(false, content);
    }
}

function getOperationName(type: string): string {
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
        case "Va":  return "variation";
        default: return "Unexpected 'type' parameter: "+type;
    }
}