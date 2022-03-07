/**Encuentra un título apropiado para el formulario de documentos comerciales.*/
export default function getTitle(type: string, flux: string): string {
    let title: string;
    switch (type) {
        case "purchase-order": title = "Nueva orden de compra "; break;
        case "remittance": title = "Nuevo remito "; break;
        case "invoice": title = "Nueva factura "; break;
        case "debit-note": title = "Nueva nota de débito "; break;
        case "credit-note": title = "Nueva nota de crédito "; break;
        case "receipt-x": title = "Nuevo recibo X "; break;
        case "receipt": title = "Nuevo recibo simple "; break;
        case "promissory-note": title = "Nuevo pagaré "; break;
        case "check": title = "Nuevo cheque "; break;
        case "other": title = "Nueva operación "; break;
        default: return "Parámetro 'type' inesperado: "+type;
    }
    switch (flux) {
        case "in": title += "de entrada"; break;
        case "out": title += "de salida"; break;
        default: return;
    }
    return title;
}