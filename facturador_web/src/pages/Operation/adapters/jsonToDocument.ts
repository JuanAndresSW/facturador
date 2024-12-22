import { documentClassCode } from "../models/operation";
import document from "../models/document";
import documentClassCodeToDocumentName from "../utilities/conversions/documentClassCodeToDocumentName";
import { base64ToBlob } from "utilities/conversions";

export default async function jsonToDocument(json: string, docCC: documentClassCode): Promise<document> {
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
            logo:               await base64ToBlob(doc.logo),
            code:               doc.senderCode? doc.senderCode : doc.senderCuit,
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
            city:               doc.receiverCity,
        },
    
        operationData: {
            sellConditions:     doc.sellConditions,
            VAT:                doc.vat,
            productTable:       doc.products
        }
    }
}