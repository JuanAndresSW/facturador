import { documentClassCode } from "../models/operation";
import document from "../models/document";
import documentClassCodeToDocumentName from "../utilities/documentClassCodeToDocumentName";

export default function jsonToDocument(json: string, docCC: documentClassCode): document {
    const doc = JSON.parse(json);
    return {
        metadata: {
            documentNumber:     doc.operationNumber,
            documentType:       doc.type,
            documentName:       documentClassCodeToDocumentName(docCC, true),
            preferenceColor:    doc.preferenceColor,
            dateOfIssue:        doc.issueDate
        },
    
        sender: {
            code:               doc.senderCode,
            name:               doc.senderName,
            address:            doc.senderAddress,
            VATCategory:        doc.senderVatCategory,
            contact:            doc.senderContact
        },
        receiver: {
            code:               doc.receiverCode,
            name:               doc.receiverName,
            address:            doc.receiverAddress,
            VATCategory:        doc.receiverVatCategory,
            postalCode:         doc.receiverPostalCode,
            locality:           doc.receiverLocality,
        },
    
        operationData: {
            sellConditions:     doc.sellConditions,
            VAT:                doc.vat,
            productTable:       doc.products
        }
    }
}