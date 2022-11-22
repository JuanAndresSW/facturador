package dev.facturador.operation.ticket.domain;

import dev.facturador.global.domain.abstractcomponents.command.Command;
import dev.facturador.operation.core.domain.model.ProductModel;
import dev.facturador.operation.fulls.domain.model.DataRequiredOperation;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public final class TicketCommand extends Command {
    @NotNull
    private List<ProductModel> products;
    private Long traderId;
    private Long id;
    private DataRequiredOperation requiredData;

}
