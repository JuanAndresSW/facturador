/**Un ítem dentro de un historial de documentos comerciales. */
type documentHistoryItem = {

    documentNumber: string,
    documentName: string,
    documentType?: string,
    
    IDOperation: number,
    dateOfIssue: string,

    receiverName: string
    receiverCUIT: string
   
}
export default documentHistoryItem;