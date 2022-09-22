export type operationCode = ("Fa" | "Oc" | "Rm" | "Rx" | "Rs" | "Nc" | "Nd" | "Pa" | "Ch");

type operation = {
    IDPointOfSale: number,
    thirdParty: {
        CUIT: string,
        name: string,
        address: string,
        contact: string,
        VATCategory: string,
        startOfActivities?: string,
        postalCode: string,
        locality: string
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
            check: string, //float
            documents: string, //float
            cash: string //float
        },
        paymentImputation: {
            type: string[],
            documentNumber: string[], //int
            amount: string[], //float
            paid: string[], //float
        },
        detailOfValues: {
            type: string[],
            bank: string[],
            documentNumber: string[], //int
            depositDate: string[],
            amount: string[], //float
        }
    },
    paymentAddress: string,
    paymentTime: string,
    description: string,
    amount: number, //float
    noProtest: boolean, 
    timeDelay: number, //int
    crossed: boolean
}
export default operation;