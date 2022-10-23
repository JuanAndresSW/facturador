import { documentClassCode } from "../models/operation";
import documentIdentifier from "../models/documentIdentifier";

export default function jsonToOperationIdentifier(json: string, classCode: documentClassCode): documentIdentifier {
    const indentifier = JSON.parse(json);
    return {
        IDOperation: indentifier.IDOperation,
        documentClassCode: classCode,
    }
}