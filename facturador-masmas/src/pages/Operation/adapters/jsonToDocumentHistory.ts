import documentHistoryItem from "../models/documentHistoryItem";

export default function jsonToDocumentHistory(json: string): documentHistoryItem[] {
    const history = JSON.parse(json);
    return history?.map((item : any): documentHistoryItem => { return {
        documentNumber: item.documentNumber,
        documentName:   item.documentName,
        documentType:   item.documentType,
        dateOfIssue:    item.dateOfIssue,
        IDBranch:       item.IDBranch,
        IDOperation:    item.IDOperation,
        receiverName:   item.receiverName
    }})
}