import {documentClassCode} from "../models/operation"

type documentIdentifier = {
    documentNumber: string,
    documentType?: "A" | "B" | "C",
    documentClassCode: documentClassCode,
    IDBranch: number
}
export default documentIdentifier;