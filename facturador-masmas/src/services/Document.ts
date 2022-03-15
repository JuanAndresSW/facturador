import fetch from 'api/fetch';

export type document = {
    documentType: ("purchase-order" | "remittance" | "invoice" | "debit-note" | "credit-note" | "receipt-x" | "receipt" | "promissory-note" | "check" | "other");
    flux: ("in"|"out");
    IDPointOfSale: number;
    sendingToGroup: boolean;
    partner: {ID:number, registered:boolean}
    IDGroup: number;
    productTable: [[number, string, number]];
    VATPercentage: string;
    observations: string;
    payer: string;
    payerAddress: string;
    paymentTime: string;
    paymentMethods: [number,number,number];
    paymentImputation: [[string,number,number,number]];
    detailOfValues: [[string,string,number,string,number]];
    seller: string;
    conditions: ("Al contado"|"Cuenta corriente"|"Cheque"|"Pagaré");
    deliveryDeadline: string;
    placeOfDelivery: string;
    carrier: string;
    descriptionOfValues: string;
    paymentDeadline: string;
    amount: number;
    protest: boolean;
    delay: number;
    bank: string;
}

export default class Document {

    /**
     * Envía los datos de un documento comercial para ser creado.
     * @param {document} document  - Datos del documento comercial, en forma de objeto.
     * @param {Function} callback  - La función que manejará la respuesta.
     */
    public static async create(document: document, callback: Function): Promise<void> {
        
    }
}