package dev.facturador.operation.fulls.application.handlers;

import dev.facturador.branch.domain.Branch;
import dev.facturador.global.domain.abstractcomponents.query.PortQueryHandler;
import dev.facturador.operation.fulls.application.CreditNoteRepository;
import dev.facturador.operation.fulls.application.DebitNoteRepository;
import dev.facturador.operation.fulls.application.InvoiceRepository;
import dev.facturador.operation.fulls.domain.entity.CreditNote;
import dev.facturador.operation.fulls.domain.entity.DebitNote;
import dev.facturador.operation.fulls.domain.entity.Invoice;
import dev.facturador.operation.shared.domain.DocumentType;
import dev.facturador.operation.fulls.application.FullOperationUtility;
import dev.facturador.operation.fulls.domain.model.FullOperationDisplayed;
import dev.facturador.operation.fulls.domain.querys.GetAnyFullOperationQuery;
import dev.facturador.trader.domain.Trader;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class GetAnyFullOperationPortQueryHandler implements PortQueryHandler<FullOperationDisplayed, GetAnyFullOperationQuery> {
    @Autowired
    private final InvoiceRepository invoiceRepository;
    @Autowired
    private final CreditNoteRepository creditRepository;
    @Autowired
    private final DebitNoteRepository debitRepository;

    @Override
    public FullOperationDisplayed handle(GetAnyFullOperationQuery query) throws Exception {
        var response = new FullOperationDisplayed();
        List<Branch> branch = new ArrayList<Branch>();
        if(query.getRepository().equals("invoice")){
            var operation = retriveInvoice(query);
            response = FullOperationUtility.resolveInvoice(operation, response);
            branch = operation.getOperation().getTraderOwner().getBranches().stream()
                    .filter(x -> x.getBranchId().equals(query.getBranchId()))
                    .collect(Collectors.toList());
        }
        if(query.getRepository().equals("debit")){
            var operation = retriveDebitNote(query);
            response = FullOperationUtility.resolveDebitNote(operation, response);
            branch = operation.getOperation().getTraderOwner().getBranches().stream()
                    .filter(x -> x.getBranchId().equals(query.getBranchId()))
                    .collect(Collectors.toList());
        }
        if(query.getRepository().equals("credit")){
            var operation = retriveCredittNote(query);
            response = FullOperationUtility.resolveCreditNote(operation, response);
            branch = operation.getOperation().getTraderOwner().getBranches().stream()
                    .filter(x -> x.getBranchId().equals(query.getBranchId()))
                    .collect(Collectors.toList());
        }

        response.setPreferenceColor(branch.get(0).getPreferenceColor());

        return response;
    }

    private Invoice retriveInvoice(GetAnyFullOperationQuery query)throws Exception{
        var invoice = invoiceRepository.findInvoice(DocumentType.valueOf(query.getType()),  new Trader(query.getTraderId()), query.getOperationNumber());
        if (Objects.isNull(invoice))
            throw new Exception("El comerciante no tiene esta factura");
        return invoice;
    }
    private DebitNote retriveDebitNote(GetAnyFullOperationQuery query)throws Exception{
        var debit = debitRepository.findDebitNote(DocumentType.valueOf(query.getType()),  new Trader(query.getTraderId()), query.getOperationNumber());
        if (Objects.isNull(debit))
            throw new Exception("El comerciante no tiene esta nota de debito");
        return debit;
    }
    private CreditNote retriveCredittNote(GetAnyFullOperationQuery query)throws Exception{
        var credit = creditRepository.findCreditNote(DocumentType.valueOf(query.getType()),  new Trader(query.getTraderId()), query.getOperationNumber());
        if (Objects.isNull(credit))
            throw new Exception("El comerciante no tiene esta nota de credito");
        return credit;
    }

}
