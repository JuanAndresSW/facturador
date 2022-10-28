export type documentClassCode = ("Fa" | "Oc" | "Rm" | "Rx" | "Rs" | "Nc" | "Nd" | "Pa" | "Ch" | "Tk");

type operation = {
    documentClassCode: documentClassCode,
    IDBranch: number,
    IDPointOfSale: number,
    thirdParty: {
        CUIT: string,
        name: string,
        address: string,
        contact: string,
        VATCategory: string,
        startOfActivities?: string,
        postalCode: string,
        city: string
    },
    productTable: {
        quantity: number[],
        description: string[],
        price: number[],
    },
    observations: string,
    seller: string,
    sellConditions: string,
    deadline: string,
    shippingAddress: string,
    carrier: string,
    remittance: string,
    VAT: number,
    receiptXTables: {
        paymentMethods: {
            check: string,
            documents: string,
            cash: string
        },
        paymentImputation: {
            type: string[],
            documentNumber: string[],
            amount: string[],
            paid: string[], 
        },
        detailOfValues: {
            type: string[],
            bank: string[],
            documentNumber: string[],
            depositDate: string[],
            amount: string[],
        }
    },
    paymentAddress: string,
    paymentTime: string,
    description: string,
    amount: number,
    noProtest: boolean, 
    timeDelay: number,
    crossed: boolean
}
export default operation;