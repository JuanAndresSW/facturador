import documentHistoryItem from "../models/documentHistoryItem";

export default function jsonToDocumentHistory(json: string): documentHistoryItem[] {
    const history = JSON.parse(json);
    return history?.map((item : any): documentHistoryItem => { return {
        IDOperation:    item.operationId,
        documentName:   item.documentClass,
        documentType:   item.documentType !== "undefined" ? item.documentType: "",
        documentNumber: item.operationNumber,
        
        receiverName:   item.receiverName !== "undefined" ? item.receiverName : "",
        receiverCUIT:   item.receiverCuit !== "undefined" ? item.receiverCuit : "",
        dateOfIssue:    item.issueDate,
    }})
}