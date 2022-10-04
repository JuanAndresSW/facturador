import { documentClassCode } from "../models/operation";
import documentIdentifier from "../models/documentIdentifier";

export default function jsonToOperationIdentifier(json: string, classCode: documentClassCode, branchID: number): documentIdentifier {
    const indentifier = JSON.parse(json);
    return {
        documentNumber: indentifier.operationNumber,
        documentType: indentifier.type,
        documentClassCode: classCode,
        IDBranch: branchID
    }
}