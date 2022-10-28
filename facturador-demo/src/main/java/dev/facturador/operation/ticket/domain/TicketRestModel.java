package dev.facturador.operation.ticket.domain;

import dev.facturador.operation.core.domain.model.ProductModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public final class TicketRestModel {
    @NotNull
    private List<ProductModel> products;
    private String pointOfSaleId;
    private String traderId;
}
