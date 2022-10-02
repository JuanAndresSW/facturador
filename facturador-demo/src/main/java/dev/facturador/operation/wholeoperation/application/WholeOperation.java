package dev.facturador.operation.wholeoperation.application;

import dev.facturador.operation.shared.domain.model.ProductModel;
import dev.facturador.operation.wholeoperation.domain.entity.CreditNote;
import dev.facturador.operation.wholeoperation.domain.entity.DebitNote;
import dev.facturador.operation.wholeoperation.domain.entity.Invoice;
import dev.facturador.operation.wholeoperation.domain.model.WholeOperationDisplayed;

import java.util.LinkedList;

public abstract class WholeOperation<T> {

    public static WholeOperationDisplayed resolveInvoice(Invoice invoice, WholeOperationDisplayed response) {
        var list = new LinkedList<ProductModel>();

        invoice.getOperation().getProducts().forEach(x ->
                list.addLast(new ProductModel(x.getQuantity(), x.getPrice(), x.getDetail()))
        );

        response.setPreferenceColor("null");
        response.setIssueDate(invoice.getIssueDate());
        response.setType(invoice.getType().toString());
        response.setVat(invoice.getVat());
        response.setOperationNumber(invoice.getInvoiceNumber());
        response.setSellConditions(invoice.getSellConditions().getCondition());

        response.setReceiverCode(invoice.getOperation().getReceiver().getReceiverCode());
        response.setReceiverAddress(invoice.getOperation().getReceiver().getReceiverAddress());
        response.setReceiverLocality(invoice.getOperation().getReceiver().getReceiverLocality());
        response.setReceiverName(invoice.getOperation().getReceiver().getReceiverName());
        response.setReceiverPostalCode(invoice.getOperation().getReceiver().getReceiverPostalCode());
        response.setReceiverVatCategory(invoice.getOperation().getReceiver().getReceiverVatCategory().toFixedVat());

        response.setSenderCode(invoice.getOperation().getSender().getSenderCode());
        response.setSenderAddress(invoice.getOperation().getSender().getSenderAddress());
        response.setSenderContact(invoice.getOperation().getSender().getSenderContact());
        response.setSenderName(invoice.getOperation().getSender().getSenderName());
        response.setSenderVatCategory(invoice.getOperation().getSender().getSenderVatCategory().vatToLowercaseAndSpanish());


        response.setProducts(list);

        return response;
    }
    public static WholeOperationDisplayed resolveDebitNote(DebitNote debitNote, WholeOperationDisplayed response) {
        var list = new LinkedList<ProductModel>();

        debitNote.getOperation().getProducts().forEach(x ->
                list.addLast(new ProductModel(x.getQuantity(), x.getPrice(), x.getDetail()))
        );

        response.setPreferenceColor("null");
        response.setIssueDate(debitNote.getIssueDate());
        response.setType(debitNote.getType().toString());
        response.setVat(debitNote.getVat());
        response.setOperationNumber(debitNote.getDebitNumber());
        response.setSellConditions(debitNote.getSellConditions().getCondition());

        response.setReceiverCode(debitNote.getOperation().getReceiver().getReceiverCode());
        response.setReceiverAddress(debitNote.getOperation().getReceiver().getReceiverAddress());
        response.setReceiverLocality(debitNote.getOperation().getReceiver().getReceiverLocality());
        response.setReceiverName(debitNote.getOperation().getReceiver().getReceiverName());
        response.setReceiverPostalCode(debitNote.getOperation().getReceiver().getReceiverPostalCode());
        response.setReceiverVatCategory(debitNote.getOperation().getReceiver().getReceiverVatCategory().toFixedVat());

        response.setSenderCode(debitNote.getOperation().getSender().getSenderCode());
        response.setSenderAddress(debitNote.getOperation().getSender().getSenderAddress());
        response.setSenderContact(debitNote.getOperation().getSender().getSenderContact());
        response.setSenderName(debitNote.getOperation().getSender().getSenderName());
        response.setSenderVatCategory(debitNote.getOperation().getSender().getSenderVatCategory().vatToLowercaseAndSpanish());


        response.setProducts(list);

        return response;
    }
    public static WholeOperationDisplayed resolveCreditNote(CreditNote creditNote, WholeOperationDisplayed response) {
        var list = new LinkedList<ProductModel>();

        creditNote.getOperation().getProducts().forEach(x ->
                list.addLast(new ProductModel(x.getQuantity(), x.getPrice(), x.getDetail()))
        );

        response.setPreferenceColor("null");
        response.setIssueDate(creditNote.getIssueDate());
        response.setType(creditNote.getType().toString());
        response.setVat(creditNote.getVat());
        response.setOperationNumber(creditNote.getCreditNumber());
        response.setSellConditions(creditNote.getSellConditions().getCondition());

        response.setReceiverCode(creditNote.getOperation().getReceiver().getReceiverCode());
        response.setReceiverAddress(creditNote.getOperation().getReceiver().getReceiverAddress());
        response.setReceiverLocality(creditNote.getOperation().getReceiver().getReceiverLocality());
        response.setReceiverName(creditNote.getOperation().getReceiver().getReceiverName());
        response.setReceiverPostalCode(creditNote.getOperation().getReceiver().getReceiverPostalCode());
        response.setReceiverVatCategory(creditNote.getOperation().getReceiver().getReceiverVatCategory().toFixedVat());

        response.setSenderCode(creditNote.getOperation().getSender().getSenderCode());
        response.setSenderAddress(creditNote.getOperation().getSender().getSenderAddress());
        response.setSenderContact(creditNote.getOperation().getSender().getSenderContact());
        response.setSenderName(creditNote.getOperation().getSender().getSenderName());
        response.setSenderVatCategory(creditNote.getOperation().getSender().getSenderVatCategory().vatToLowercaseAndSpanish());


        response.setProducts(list);

        return response;
    }
}
