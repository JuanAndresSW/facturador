import { documentClassCode } from "../../models/operation";


/**Convierte un código de clase a un nombre de documento.*/
export default function documentClassCodeToDocumentName(cc: documentClassCode, inSpanish = false) {
    switch (cc) {
        case "Oc":  return inSpanish ? "Orden de compra" : "purchase-order";
        case "Rm":  return inSpanish ? "Remito" : "remittance";
        case "Fa":  return inSpanish ? "Factura" : "invoice";
        case "Nd":  return inSpanish ? "Nota de débito" : "debit-note";
        case "Nc":  return inSpanish ? "Nota de crédito" : "credit-note";
        case "Rx":  return inSpanish ? "Recibo X" : "receipt-x";
        case "Rs":  return inSpanish ? "Recibo" : "receipt";
        case "Pa":  return inSpanish ? "Pagaré" : "promissory-note";
        case "Ch":  return inSpanish ? "Cheque" : "check";
        default:    return "";
    }
}