package dev.facturador.operation.fulls.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.facturador.operation.shared.domain.model.ProductModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullOperationRestModel implements Serializable {
    public static final Long serialVersionUID = 1L;

    @JsonProperty("IDPointOfSale")
    @NotNull
    private long IDPointOfSale;

    @JsonProperty("IDTrader")
    @NotNull
    private long IDTrader;

    @NotEmpty
    private String sellConditions;

    @NotNull
    private int vat;

    @NotNull
    private List<ProductModel> products;

    @Size(min = 3, max = 30)
    @NotEmpty
    private String receiverName;
    @Size(max = 15)
    @NotEmpty
    private String receiverCode;
    @NotEmpty
    private String receiverAddress;
    @NotEmpty
    private String receiverVatCategory;
    @Size(min = 4, max = 8)
    @NotEmpty
    private String receiverPostalCode;
    @NotEmpty
    private String receiverLocality;

}
