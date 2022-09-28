import Valid from 'utilities/Valid';
import operationFilters from "./operationFilters";
import documentProp from '../models/documentProp';
import operation, { documentClassCode } from '../models/operation';


/**Devuelve un boolean representando la validez de un objeto de operación. Envía un mensaje de error a una función.*/
export default function isValidOperation(operation: operation, documentClassCode: documentClassCode, setError: Function): boolean {

    //Validar datos del comerciante.
    if (!Number.isInteger(operation.IDPointOfSale))
    return no("Seleccione un punto de venta.");


    //Validar datos del tercero.
    if (currentOperationIncludes("receiverCUIT") && !Valid.CUIT(operation.thirdParty.CUIT, setError))
    return false;

    if (currentOperationIncludes("receiverName") && !Valid.names(operation.thirdParty.name, setError))
    return false;

    if (currentOperationIncludes("receiverAddress") && !Valid.address(operation.thirdParty.address))
    return no("Ingrese una dirección entre 4 y 40 caracteres.");

    if (currentOperationIncludes("receiverVATCategory") && !Valid.vatCategory(operation.thirdParty.VATCategory, setError))
    return false;

    if (currentOperationIncludes("receiverPostalCode") && !Valid.postalCode(operation.thirdParty.postalCode, setError))
    return false;

    if (currentOperationIncludes("receiverLocality") && !Valid.address(operation.thirdParty.locality))
    return no("Ingrese una localidad de entre 4 y 40 caracteres.");


    //Validar datos de la operación.
    if (currentOperationIncludes("productTable")) {

        operation.productTable.quantity.forEach((cell, index)=>{
            if (cell < 1 || cell > 9999999)     return no(`La cantidad de la fila ${index+1} de la tabla de productos debe ser entre 1 y 9.999.999.`);
        });

        operation.productTable.description.forEach((cell, index)=>{
            if (cell.length < 5) return no(`La descripción de la fila ${index+1} de la tabla de productos debe ser de al menos 5 caracteres.`);
        });

        operation.productTable.quantity.forEach((cell, index)=>{
            if (cell < 1 || cell > 9999999)   return no(`El precio de la fila ${index+1} de la tabla de productos debe ser entre 1 y 9.999.999.`);
        });

    }

    if (currentOperationIncludes("observations") && operation.observations?.length > 30)
    return no("Las observaciones no pueden exceder los 30 caracteres.");

    if (currentOperationIncludes("seller") &&  operation.seller?.length > 20)
    return no("El nombre del vendedor no puede exceder los 20 caracteres.");

    if (currentOperationIncludes("sellConditions") && !Valid.sellConditions(operation.sellConditions, setError))
    return false;

    if (currentOperationIncludes("deadline") && operation.deadline && !Valid.date(operation.deadline))
    return no("Ingrese una fecha límite");

    if (currentOperationIncludes("shippingAddress") && operation.shippingAddress?.length > 20)
    return no("La dirección de entrega no debe superar los 20 caracteres.");

    if (currentOperationIncludes("carrier") && operation.carrier?.length > 20)
    return no("El nombre del transportista no debe superar los 20 caracteres.");

    if (currentOperationIncludes("paymentMethods") && (
       parseFloat(operation.receiptXTables.paymentMethods.cash)         <1
    || parseFloat(operation.receiptXTables.paymentMethods.documents)    <1
    || parseFloat(operation.receiptXTables.paymentMethods.check)        <1 
    || parseFloat(operation.receiptXTables.paymentMethods.cash) + parseFloat(operation.receiptXTables.paymentMethods.documents) + parseFloat(operation.receiptXTables.paymentMethods.check) <= 0))
    return no("El importe total no puede ser 0 o contener importes negativos.");

    if (currentOperationIncludes("paymentImputation")) {
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

    }

    if (currentOperationIncludes("detailOfValues")) {

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

        if (operation.paymentAddress && !Valid.address(operation.paymentAddress))
        return no("La dirección de pago ingresada debe ser de entre 4 y 40 caracteres.");
    }

    if (currentOperationIncludes("description") && (operation.description.length < 5 || operation.description.length > 20))
    return no("La descripción debe ser de entre 5 y 20 caracteres");

    if (currentOperationIncludes("amount") && operation.amount <= 0)
    return no("El monto debe ser mayor a 0");

    if (currentOperationIncludes("timeDelay") && operation.timeDelay && operation.timeDelay < 0 || operation.timeDelay > 365)
    return no("La diferencia de tiempo debe ser de entre 0 y 365 días.");

    function no(messaje: string):   boolean {setError(messaje);return false;}
    function currentOperationIncludes(thisProperty: documentProp): boolean { return operationFilters[thisProperty].includes(documentClassCode); }
}