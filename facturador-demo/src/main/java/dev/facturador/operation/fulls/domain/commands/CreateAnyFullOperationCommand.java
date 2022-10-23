package dev.facturador.operation.fulls.domain.commands;

import dev.facturador.global.domain.abstractcomponents.command.Command;
import dev.facturador.operation.fulls.domain.model.FullOperationRestModel;
import dev.facturador.operation.fulls.domain.model.DataRequiredOperation;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateAnyFullOperationCommand extends Command {
    private final FullOperationRestModel invoiceValues;
    private final DataRequiredOperation internalValues;
    private final String repository;
    private Long id;
}
