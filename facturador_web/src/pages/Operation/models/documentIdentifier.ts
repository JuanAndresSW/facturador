import {documentClassCode} from "../models/operation"

/**Un objeto para identificar inequívocamente a un documento comercial. */
type documentIdentifier = {
    IDOperation: number,
    documentClassCode: documentClassCode,
}
export default documentIdentifier;