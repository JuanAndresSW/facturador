package dev.facturador.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Modelo del Json para la cuenta
 */
@Data
public final class Account {

    //Anotaciones para validar el modelo (Ejemplo NotNull dice que no puede recibir un nulo)
    @NotNull
    private User user;
    @NotNull
    private Trader trader;
}
