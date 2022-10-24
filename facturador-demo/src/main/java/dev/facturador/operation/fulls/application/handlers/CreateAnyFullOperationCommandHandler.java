package dev.facturador.operation.fulls.application.handlers;

import dev.facturador.global.domain.abstractcomponents.command.PortCommandHandler;
import dev.facturador.operation.fulls.application.CreditNoteRepository;
import dev.facturador.operation.fulls.application.DebitNoteRepository;
import dev.facturador.operation.fulls.application.InvoiceRepository;
import dev.facturador.operation.fulls.domain.entity.CreditNote;
import dev.facturador.operation.fulls.domain.entity.DebitNote;
import dev.facturador.operation.fulls.domain.entity.Invoice;
import dev.facturador.operation.fulls.domain.commands.CreateAnyFullOperationCommand;
import dev.facturador.operation.core.application.OperationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@AllArgsConstructor
@Transactional(propagation = Propagation.REQUIRED)
@Service
public class CreateAnyFullOperationCommandHandler implements PortCommandHandler<CreateAnyFullOperationCommand> {
    @Autowired
    private final InvoiceRepository invoiceRepository;
    @Autowired
    private final CreditNoteRepository creditRepository;
    @Autowired
    private final DebitNoteRepository debitRepository;
    @Autowired
    private final OperationRepository operationRepository;

    @Override
    public void handle(CreateAnyFullOperationCommand command) throws Exception {

        if(command.getRepository().equals("invoice")){
            var invoice = Invoice.create(command.getInvoiceValues(), command.getInternalValues());
            var operation = operationRepository.save(invoice.getOperation());
            invoice.setOperation(operation);
            invoice = invoiceRepository.save(invoice);
            command.setId(invoice.getInvoiceId());
        }
        if(command.getRepository().contains("debit")) {
            var debit = DebitNote.create(command.getInvoiceValues(), command.getInternalValues());
            var operation = operationRepository.save(debit.getOperation());
            debit.setOperation(operation);
            debit = debitRepository.save(debit);
            command.setId(debit.getDebitId());
        }
        if(command.getRepository().contains("credit")) {
            var credit = CreditNote.create(command.getInvoiceValues(), command.getInternalValues());
            var operation = operationRepository.save(credit.getOperation());
            credit.setOperation(operation);
            credit = creditRepository.save(credit);
            command.setId(credit.getCreditId());
        }


    }
}