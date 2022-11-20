package dev.facturador.operation.ticket.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.facturador.operation.core.domain.model.ProductModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public final class TicketRestModel {
    @JsonProperty(value = "IDPointOfSale")
    @NotNull
    private String IDPointOfSale;
    @JsonProperty(value = "IDTrader")
    @NotNull
    private String IDTrader;
    @NotNull
    private List<ProductModel> products;
}
