package dev.facturador.operation.shared.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel implements Serializable {
    public static final Long serialVersionUID = 1L;
    private Integer quantity;
    private Double price;
    private String detail;
}
