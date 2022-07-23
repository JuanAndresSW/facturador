type operation = {
    IDPointOfSale: number,
    thirdParty: {
        CUIT: string,
        name: string,
        address: string,
        phone: string,
        VATCategory: string,
        email: string,
        startOfActivities: string,
        postalCode: string,
        locality: string
    },
    productTable: {
        quantity: string[], //int
        description: string[],
        price: string[], //float
    },
    observations: string,
    seller: string,
    sellConditions: string,
    deadline: string,
    placeOfDelivery: string,
    carrier: string,
    IDRemittance: number, //int
    tax: number,
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
    payementAddress: string,
    paymentTime: string,
    description: string,
    amount: number, //float
    noProtest: boolean, 
    timeDelay: number, //int
    crossed: boolean
}
export default operation;