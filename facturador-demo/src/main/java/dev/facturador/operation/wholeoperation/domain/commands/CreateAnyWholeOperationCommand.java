package dev.facturador.operation.wholeoperation.domain.commands;

import dev.facturador.global.domain.abstractcomponents.commands.Command;
import dev.facturador.operation.wholeoperation.domain.model.WholeOperationRestModel;
import dev.facturador.operation.wholeoperation.domain.model.DataReququiredOperation;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateAnyWholeOperationCommand extends Command {
    private final WholeOperationRestModel invoiceValues;
    private final DataReququiredOperation internalValues;
    private final String repository;
}
