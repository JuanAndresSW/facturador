package dev.facturador.dto;

import lombok.Data;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Modelo del Json para la cuenta
 */
@Data
public final class Account {

    //Anotaciones para validar el modelo (Ejemplo NotNull dice que no puede recibir un nulo)
    @NotNull
    @Valid
    private User user;
    @NotNull
    @Valid
    private Trader trader;
}
