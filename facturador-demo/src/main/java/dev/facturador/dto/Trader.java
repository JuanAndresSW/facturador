package dev.facturador.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Dto del Json para el Comerciante
 */
@Data
public final class Trader {

    @NotNull
    private String businessName;
    @NotNull
    private String vatCategory;
    @NotNull
    private String code;

    private String grossIncome;
}
