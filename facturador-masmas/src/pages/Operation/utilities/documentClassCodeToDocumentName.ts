import { documentClassCode } from "../models/operation";


/**Convierte un código de clase a un nombre de documento.*/
export default function documentClassCodeToDocumentName(cc: documentClassCode, userFriendly = false) {
    switch (cc) {
        case "Oc":  return userFriendly ? "Orden de compra" : "purchase-order";
        case "Rm":  return userFriendly ? "Remito" : "remittance";
        case "Fa":  return userFriendly ? "Factura" : "invoice";
        case "Nd":  return userFriendly ? "Nota de débito" : "debit-note";
        case "Nc":  return userFriendly ? "Nota de crédito" : "credit-note";
        case "Rx":  return userFriendly ? "Recibo X" : "receipt-x";
        case "Rs":  return userFriendly ? "Recibo" : "receipt";
        case "Pa":  return userFriendly ? "Pagaré" : "promissory-note";
        case "Ch":  return userFriendly ? "Cheque" : "check";
        default:    return userFriendly ? "Error" : "Unexpected parameter " + cc;
    }
}