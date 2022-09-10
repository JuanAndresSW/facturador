package dev.facturador.operation.invoice.application.handlers;

import dev.facturador.global.domain.abstractcomponents.querys.QueryHandler;
import dev.facturador.operation.invoice.application.InvoiceRepository;
import dev.facturador.operation.invoice.domain.Invoice;
import dev.facturador.operation.shared.application.OperationService;
import dev.facturador.operation.shared.domain.DocumentType;
import dev.facturador.operation.shared.domain.model.WholeOperationDisplayed;
import dev.facturador.operation.invoice.domain.querys.GetInvoiceQuery;
import dev.facturador.operation.shared.domain.model.ProductModel;
import dev.facturador.trader.domain.Trader;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class GetInvoiceQueryHandler implements QueryHandler<WholeOperationDisplayed, GetInvoiceQuery> {
    @Autowired
    private final InvoiceRepository repository;


    @Override
    public WholeOperationDisplayed handle(GetInvoiceQuery query) throws Exception {
        var response = new WholeOperationDisplayed();
        if(query.getRepository().equalsIgnoreCase("invoice")){
            var invoice = repository.findInvoice
                    (DocumentType.valueOf(query.getType()),  new Trader(query.getTraderId()), query.getInvoiceNumber());
            log.info("invoice is: {}", invoice);
            if (Objects.isNull(invoice))
                throw new Exception("El comerciante no tiene esta factura");
            response = this.toResponse(invoice, response);
        }

        return response;
    }

    private WholeOperationDisplayed toResponse(Invoice invoice, WholeOperationDisplayed response) {
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
}
