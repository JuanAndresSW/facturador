package dev.facturador.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;
/**
 * Dto del Json para el Comerciante
 */
@Data
public final class Trader {

    @NotNull(message = "Cannot be null")
    @Size(min = 3, max = 20, message = "invalid size")
    private String businessName;
    @NotNull(message = "Cannot be null")
    private String vatCategory;
    @NotNull(message = "Cannot be null")
    @Size(min = 11, max = 11, message = "invalid size")
    private String code;

    private String grossIncome;
}
