package dev.facturador.operation.wholeoperation.application.handlers;

import dev.facturador.global.domain.abstractcomponents.querys.QueryHandler;
import dev.facturador.operation.wholeoperation.application.CreditNoteRepository;
import dev.facturador.operation.wholeoperation.application.DebitNoteRepository;
import dev.facturador.operation.wholeoperation.application.InvoiceRepository;
import dev.facturador.operation.wholeoperation.domain.entity.CreditNote;
import dev.facturador.operation.wholeoperation.domain.entity.DebitNote;
import dev.facturador.operation.wholeoperation.domain.entity.Invoice;
import dev.facturador.operation.shared.domain.DocumentType;
import dev.facturador.operation.wholeoperation.application.WholeOperation;
import dev.facturador.operation.wholeoperation.domain.model.WholeOperationDisplayed;
import dev.facturador.operation.wholeoperation.domain.querys.GetAnyWholeOperationQuery;
import dev.facturador.trader.domain.Trader;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@AllArgsConstructor
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class GetAnyWholeOperationQueryHandler implements QueryHandler<WholeOperationDisplayed, GetAnyWholeOperationQuery> {
    @Autowired
    private final InvoiceRepository invoiceRepository;
    @Autowired
    private final CreditNoteRepository creditRepository;
    @Autowired
    private final DebitNoteRepository debitRepository;

    @Override
    public WholeOperationDisplayed handle(GetAnyWholeOperationQuery query) throws Exception {
        var response = new WholeOperationDisplayed();

        if(query.getRepository().equals("invoice")){
            var operation = retriveInvoice(query);
            response = WholeOperation.resolveInvoice(operation, response);
        }
        if(query.getRepository().equals("debit")){
            var operation = retriveDebitNote(query);
            response = WholeOperation.resolveDebitNote(operation, response);
        }
        if(query.getRepository().equals("credit")){
            var operation = retriveCredittNote(query);
            response = WholeOperation.resolveCreditNote(operation, response);
        }

        return response;
    }

    private Invoice retriveInvoice(GetAnyWholeOperationQuery query)throws Exception{
        var invoice = invoiceRepository.findInvoice(DocumentType.valueOf(query.getType()),  new Trader(query.getTraderId()), query.getOperationNumber());
        if (Objects.isNull(invoice))
            throw new Exception("El comerciante no tiene esta factura");
        return invoice;
    }
    private DebitNote retriveDebitNote(GetAnyWholeOperationQuery query)throws Exception{
        var debit = debitRepository.findDebitNote(DocumentType.valueOf(query.getType()),  new Trader(query.getTraderId()), query.getOperationNumber());
        if (Objects.isNull(debit))
            throw new Exception("El comerciante no tiene esta nota de debito");
        return debit;
    }
    private CreditNote retriveCredittNote(GetAnyWholeOperationQuery query)throws Exception{
        var credit = creditRepository.findCreditNote(DocumentType.valueOf(query.getType()),  new Trader(query.getTraderId()), query.getOperationNumber());
        if (Objects.isNull(credit))
            throw new Exception("El comerciante no tiene esta nota de credito");
        return credit;
    }

}
