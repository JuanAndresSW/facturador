package dev.facturador.operation.invoice.application.handlers;

import dev.facturador.global.domain.abstractcomponents.commands.CommandHandler;
import dev.facturador.operation.invoice.application.InvoiceRepository;
import dev.facturador.operation.invoice.domain.Invoice;
import dev.facturador.operation.invoice.domain.commands.CreateInvoiceCommand;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Transactional(propagation = Propagation.REQUIRED)
@Service
public class SenderInvoiceCommandHandler implements CommandHandler<CreateInvoiceCommand> {
    @Autowired
    private final InvoiceRepository repository;

    @Override
    public void handle(CreateInvoiceCommand command) throws Exception {
        var invoice = Invoice.create(command.getInvoiceValues(), command.getInternalValues());
        repository.save(invoice);
    }
}
