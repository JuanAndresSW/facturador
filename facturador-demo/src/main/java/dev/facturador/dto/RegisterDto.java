package dev.facturador.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/*
Formato del JSON
{
"user": {
    "username": "fdda",
    "email": "fadfsa@gmail.com",
    "password": "1234231244",
    "avatar": "gfdgdfgdfgreyrthtyujuytjyujkyukuykuykyjh6"
      },
"trader": {
        "businessName": "Jesus LC",
        "vatCategory": "Mono tributista",
        "code": "23453217245",
        "grossIncome": "14-49.543.762-4"
      }
}
 */

/**
 * Dto para cuando se registra
 */
@Data
public final class RegisterDto {

    //Anotaciones para validar el modelo (Ejemplo NotNull dice que no puede recibir un nulo)
    @JsonProperty("user")
    @NotNull
    @Valid
    private UserDto userDto;
    @JsonProperty("trader")
    @NotNull
    @Valid
    private TraderDto traderDto;
}
