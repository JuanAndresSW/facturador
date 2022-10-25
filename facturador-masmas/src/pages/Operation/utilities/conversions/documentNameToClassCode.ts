import { documentClassCode } from "../../models/operation";


/**Convierte un nombre de documento en un código de clase de documento.*/
export default function documentNameToClassCode(name: string): documentClassCode {

    name = name.toLocaleLowerCase();

    if (name.includes("orden de compra"))   return "Oc"
    if (name.includes("remito"))            return "Rm"
    if (name.includes("factura"))           return "Fa"
    if (name.includes("débito"))            return "Nd"
    if (name.includes("crédito"))           return "Nc"
    if (name.includes("recibo x"))          return "Rx"
    if (name.includes("recibo"))            return "Rs"
    if (name.includes("pagaré"))            return "Pa"
    if (name.includes("cheque"))            return "Ch"
    
    return null;
    
}