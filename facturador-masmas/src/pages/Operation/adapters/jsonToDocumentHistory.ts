import documentHistoryItem from "../models/documentHistoryItem";

export default function jsonToDocumentHistory(json: string): documentHistoryItem[] {
    const history = JSON.parse(json);
    return history?.map((item : any): documentHistoryItem => { return {
        IDOperation:    item.operationId,
        documentName:   item.documentClass,
        documentType:   item.documentType,
        documentNumber: item.operationNumber,
        
        receiverName:   item.receiverName,
        receiverCUIT:   item.receiverCuit,
        dateOfIssue:    item.issueDate,
        
        
    }})
}