type operationIdentifier = {
    operationNumber: string,
    operationType: 
    "Factura A" |
    "Factura B" | 
    "Factura C" | 
    "Nota de crédito A" |
    "Nota de crédito B" |
    "Nota de crédito C" | 
    "Nota de débito A" | 
    "Nota de débito B" | 
    "Nota de débito C",
    dateOfIssue: string
}
export default operationIdentifier;