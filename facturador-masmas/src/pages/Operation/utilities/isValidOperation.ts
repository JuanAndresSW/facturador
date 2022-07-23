import Valid from 'utilities/Valid';
import operation from '../models/operation';

/**Devuelve un boolean representando la validez de un objeto de operación. Envía un mensaje de error a una función.*/
export default function isValidOperation(operation: operation, type: string, toSend: boolean, setError: Function): boolean {

    //Validar datos del tercero.
    if (!Number.isInteger(operation.IDPointOfSale))
    return no("Seleccione un punto de venta.");

    if ((!is("RePaVa") && !toSend) || (!is("RePaVaCh") && toSend))
    return Valid.CUIT(operation.thirdParty.CUIT, setError);

    if (!is("Va"))
    return Valid.names(operation.thirdParty.name, setError);

    if (((!is("ReVa") && !toSend) || (!is("ReVaCh") && toSend)) && !Valid.address(operation.thirdParty.address))
    return no("Ingrese una dirección entre 4 y 40 caracteres.");

    if (!is("ReVaCh") && !toSend)
    return Valid.phone(operation.thirdParty.phone, setError);

    if (!is("RePaChVa"))
    return Valid.vatCategory(operation.thirdParty.VATCategory, setError);

    if (!toSend && !is("RePaChVa"))
    return Valid.email(operation.thirdParty.email, setError);

    if (!toSend && !is("RePaChVa") && !Valid.date(operation.thirdParty.startOfActivities))
    return no("Ingrese una fecha de inicio de actividades");

    if (toSend && !is("RePaChVa"))
    return Valid.postalCode(operation.thirdParty.postalCode, setError);

    if (toSend && !is("RePaVa") && !Valid.address(operation.thirdParty.locality))
    return no("Ingrese una localidad de entre 4 y 40 caracteres.");


    //Validar datos de la operación.
    if (is("OcRmFaNdNc")) {

        operation.productTable.quantity.forEach((cell, index)=>{
            if (parseInt(cell) < 1)     return no(`La cantidad de la fila ${index+1} de la tabla de productos debe ser mayor a 0.`);
        });

        operation.productTable.description.forEach((cell, index)=>{
            if (cell.length < 5)        return no(`La descripción de la fila ${index+1} de la tabla de productos debe ser de al menos 5 caracteres.`);
        });

        operation.productTable.quantity.forEach((cell, index)=>{
            if (parseFloat(cell) < 1)   return no(`El precio de la fila ${index+1} de la tabla de productos debe ser mayor a 0.`);
        });

    }

    if (is("OcRm") && operation.observations && operation.observations.length <= 50)
    return no("Las observaciones no pueden exceder los 50 caracteres.");

    if (is("Oc") && operation.seller && Valid.names(operation.seller))
    return no("El nombre del vendedor debe ser de entre 3 y 20 caracteres.");

    if (is("OcFaNdNc") && operation.sellConditions && !"Al contado Cuenta corriente Cheque Pagaré Al contado".includes(operation.sellConditions))
    return no("Las condiciones de venta deben ser: Al contado, Cuenta corriente, Cheque, Pagaré o Al contado");

    if (is("RePa") && !Valid.date(operation.deadline))
    return no("Ingrese una fecha límite");

    if (is("Oc") && operation.placeOfDelivery && !Valid.address(operation.placeOfDelivery))
    return no("La dirección de entrega debe ser de entre 4 y 40 caracteres.");

    if (is("Oc") && operation.carrier && !Valid.names(operation.carrier))
    return no("El nombre del transportista debe ser de entre 3 y 20 caracteres.");

    if (is("Rx") && (
       parseFloat(operation.receiptXTables.paymentMethods.cash)         <1
    || parseFloat(operation.receiptXTables.paymentMethods.documents)    <1
    || parseFloat(operation.receiptXTables.paymentMethods.check)        <1 
    || parseFloat(operation.receiptXTables.paymentMethods.cash) + parseFloat(operation.receiptXTables.paymentMethods.documents) + parseFloat(operation.receiptXTables.paymentMethods.check) <= 0))
    return no("El importe total no puede ser 0 o contener importes negativos.");

    if (is("Rx")) {
        operation.receiptXTables.paymentImputation.type.forEach((cell, index)=>{
            if (cell.length < 1) return no(`El tipo en la fila ${index} de la tabla de imputación de pago no puede estar vacío.`);
        });
        operation.receiptXTables.paymentImputation.documentNumber.forEach((cell, index)=>{
            if (cell.length !== 10) return no(`El número de documento en la fila ${index} de la tabla de imputación de pago debe ser de 10 caracteres.`);
        });
        operation.receiptXTables.paymentImputation.amount.forEach((cell, index)=>{
            if (parseFloat(cell) <= 0) return no(`El monto en la fila ${index} de la tabla de imputación de pago debe ser mayor a 0.`);
        });
        operation.receiptXTables.paymentImputation.paid.forEach((cell, index)=>{
            if (parseFloat(cell) <= 0 || parseFloat(cell) > parseFloat(operation.receiptXTables.paymentImputation.amount[index]))
            return no(`El monto abonado en la fila ${index} de la tabla de imputación de pago debe ser mayor a 0 y no mayor al total.`);
        });

        operation.receiptXTables.detailOfValues.type.forEach((cell, index)=>{
            if (cell.length < 1) return no(`El tipo en la fila ${index} de la tabla de detalles de valores no puede estar vacío.`);
        });
        operation.receiptXTables.detailOfValues.bank.forEach((cell, index)=>{
            if (cell.length < 1) return no(`El banco en la fila ${index} de la tabla de detalles de valores no puede estar vacío.`);
        });
        operation.receiptXTables.detailOfValues.documentNumber.forEach((cell, index)=>{
            if (cell.length < 1) return no(`El número de documento en la fila ${index} de la tabla de detalles de valores no puede estar vacío.`);
        });
        operation.receiptXTables.detailOfValues.depositDate.forEach((cell, index)=>{
            if (!Valid.date(cell)) return no(`La fecha en la fila ${index} de la tabla de detalles de valores no es válida.`);
        });
        operation.receiptXTables.detailOfValues.amount.forEach((cell, index)=>{
            if (parseFloat(cell) <= 0) return no(`El monto en la fila ${index} de la tabla de detalles de valores debe ser mayor a 0.`);
        });

        if (operation.payementAddress && !Valid.address(operation.payementAddress))
        return no("La dirección de pago ingresada debe ser de entre 4 y 40 caracteres.");
    }

    if (is("RePaVa") && (operation.description.length < 5 || operation.description.length > 20))
    return no("La descripción debe ser de entre 5 y 20 caracteres");

    if (is("RePaChVa") && operation.amount <= 0)
    return no("El monto debe ser mayor a 0");

    if (is("Ch") && operation.timeDelay && operation.timeDelay < 0)
    return no("La diferencia de tiempo no puede ser negativa.");

    function no(messaje: string):   boolean {setError(messaje);return false;}
    function is(types: string):     boolean {return types.includes(type);}
}