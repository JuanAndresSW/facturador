package dev.facturador.operation.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel implements Serializable {
    public static final Long serialVersionUID = 1L;

    @NotNull
    private Integer quantity;
    @NotNull
    private Double price;
    @NotEmpty
    private String detail;
}
