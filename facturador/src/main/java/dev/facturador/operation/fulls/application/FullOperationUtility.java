package dev.facturador.operation.fulls.application;

import dev.facturador.operation.core.domain.entity.Operation;
import dev.facturador.operation.core.domain.model.ProductModel;
import dev.facturador.operation.fulls.domain.entity.CreditNote;
import dev.facturador.operation.fulls.domain.entity.DebitNote;
import dev.facturador.operation.fulls.domain.entity.Invoice;
import dev.facturador.operation.fulls.domain.model.FullOperationDisplayed;
import lombok.extern.log4j.Log4j2;

import java.util.LinkedList;

@Log4j2
public class FullOperationUtility {

    private FullOperationUtility() {
        throw new IllegalStateException("Utility class");
    }
    private static void resolveOperation(Operation operation, FullOperationDisplayed response){
        var list = new LinkedList<ProductModel>();
        operation.getProducts().forEach(x ->
                list.addLast(new ProductModel(x.getQuantity(), x.getPrice(), x.getDetail()))
        );
        response.setIssueDate(operation.getIssueDate());

        response.setReceiverCode(operation.getReceiver().getReceiverCode());
        response.setReceiverAddress(operation.getReceiver().getReceiverAddress());
        response.setReceiverName(operation.getReceiver().getReceiverName());
        response.setReceiverPostalCode(operation.getReceiver().getReceiverPostalCode());
        response.setReceiverVatCategory(operation.getReceiver().getReceiverVatCategory().toFixedVat());
        response.setReceiverCity(operation.getReceiver().getReceiverCity());

        response.setSenderCode(operation.getSender().getSenderCode());
        response.setSenderAddress(operation.getSender().getSenderAddress());
        response.setSenderContact(operation.getSender().getSenderContact());
        response.setSenderName(operation.getSender().getSenderName());
        response.setSenderVatCategory(operation.getSender().getSenderVatCategory().vatToLowercaseAndSpanish());

        response.setProducts(list);
    }

    public static void resolveInvoice(Invoice invoice, FullOperationDisplayed response) {
        response.setType(invoice.getType().toString());
        response.setVat(invoice.getVat());
        response.setOperationNumber(invoice.getInvoiceNumber());
        response.setSellConditions(invoice.getSellConditions().getCondition());

        resolveOperation(invoice.getOperation(), response);
    }

    public static void resolveDebitNote(DebitNote debitNote, FullOperationDisplayed response) {
        response.setType(debitNote.getType().toString());
        response.setVat(debitNote.getVat());
        response.setOperationNumber(debitNote.getDebitNumber());
        response.setSellConditions(debitNote.getSellConditions().getCondition());

        resolveOperation(debitNote.getOperation(), response);
    }

    public static void resolveCreditNote(CreditNote creditNote, FullOperationDisplayed response) {
        response.setType(creditNote.getType().toString());
        response.setVat(creditNote.getVat());
        response.setOperationNumber(creditNote.getCreditNumber());
        response.setSellConditions(creditNote.getSellConditions().getCondition());

        resolveOperation(creditNote.getOperation(), response);
    }
}
