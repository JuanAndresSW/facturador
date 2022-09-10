package dev.facturador.operation.wholeoperation.application.handlers;

import dev.facturador.global.domain.abstractcomponents.commands.CommandHandler;
import dev.facturador.operation.wholeoperation.application.CreditNoteRepository;
import dev.facturador.operation.wholeoperation.application.DebitNoteRepository;
import dev.facturador.operation.wholeoperation.application.InvoiceRepository;
import dev.facturador.operation.wholeoperation.domain.entity.CreditNote;
import dev.facturador.operation.wholeoperation.domain.entity.DebitNote;
import dev.facturador.operation.wholeoperation.domain.entity.Invoice;
import dev.facturador.operation.wholeoperation.domain.commands.CreateAnyWholeOperationCommand;
import dev.facturador.trader.domain.Trader;
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
public class CreateAnyWholeOperationCommandHandler implements CommandHandler<CreateAnyWholeOperationCommand> {
    @Autowired
    private final InvoiceRepository invoiceRepository;
    @Autowired
    private final CreditNoteRepository creditRepository;
    @Autowired
    private final DebitNoteRepository debitRepository;

    @Override
    public void handle(CreateAnyWholeOperationCommand command) throws Exception {
        log.info("entra al comando?");
        if(command.getRepository().equals("invoice")){
            var invoice = Invoice.create(command.getInvoiceValues(), command.getInternalValues());
            log.info("invoice is: {}", invoice);
            invoiceRepository.save(invoice);
        }
        if(command.getRepository().contains("debit")) {
            var debit = DebitNote.create(command.getInvoiceValues(), command.getInternalValues());
            debitRepository.save(debit);
        }
        if(command.getRepository().contains("credit")) {
            var credit = CreditNote.create(command.getInvoiceValues(), command.getInternalValues());
            creditRepository.save(credit);
        }
    }
}
