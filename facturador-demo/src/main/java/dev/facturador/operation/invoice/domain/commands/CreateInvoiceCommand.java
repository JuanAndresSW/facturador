package dev.facturador.operation.invoice.domain.commands;

import dev.facturador.global.domain.abstractcomponents.commands.Command;
import dev.facturador.operation.shared.domain.model.WholeOperationRestModel;
import dev.facturador.operation.shared.domain.model.DataReququiredOperation;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateInvoiceCommand extends Command {
    private final WholeOperationRestModel invoiceValues;
    private final DataReququiredOperation internalValues;
}
