type document = {
    metadata: {
        documentNumber: string,
        documentType: string,
        documentName: string,
        preferenceColor: string,
        dateOfIssue: string
    },

    sender: {
        logo: Blob,
        code: string,
        name: string,
        address: string
        VATCategory: string,
        contact: string
    },
    receiver: {
        code: string,
        name: string,
        address: string,
        VATCategory: string,
        postalCode: string,
        locality: string,
    },

    operationData: {
        sellConditions: string,
        VAT: number,
        productTable: {
            quantity: number,
            price: number,
            detail: string
        }[],

    }
}
export default document;