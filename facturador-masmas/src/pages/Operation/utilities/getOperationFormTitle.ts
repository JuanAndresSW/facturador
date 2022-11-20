import { documentClassCode } from "../models/operation";


/**Encuentra un título apropiado para el formulario de documentos comerciales.*/
export default function getOperationFormTitle(type: documentClassCode, toSend: boolean): string {
    let title: string;
    switch (type) {
        case "Fa":  title = "Nueva factura ";         break;
        case "Nd":  title = "Nueva nota de débito ";  break;
        case "Nc":  title = "Nueva nota de crédito "; break;
        case "Tk":  return  "Nuevo ticket";
        case "Oc":  title = "Nueva orden de compra "; break;
        case "Rm":  title = "Nuevo remito ";          break;
        case "Rx":  title = "Nuevo recibo X ";        break;
        case "Rs":  title = "Nuevo recibo simple ";   break;
        case "Pa":  title = "Nuevo pagaré ";          break;
        case "Ch":  title = "Nuevo cheque ";          break;
        default: return "Parámetro 'type' inesperado: "+type;
    }
    if (!toSend) return title + "de entrada";
    else return title + "de salida";
}