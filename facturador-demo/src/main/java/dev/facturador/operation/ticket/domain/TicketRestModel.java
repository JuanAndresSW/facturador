package dev.facturador.operation.ticket.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.facturador.operation.core.domain.model.ProductModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public final class TicketRestModel {
    @NotNull
    private List<ProductModel> products;
    @JsonProperty(value = "IDPointOfSale")
    private String IDPointOfSale;
    @JsonProperty(value = "IDTrader")
    private String IDTrader;
}
