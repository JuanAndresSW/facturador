package dev.facturador.modelo;

import lombok.Data;

import javax.validation.constraints.NotNull;

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
