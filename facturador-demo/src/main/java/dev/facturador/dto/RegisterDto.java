package dev.facturador.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/*
Formato del JSON
{
"user": {
    "username": "reimus",
    "email": "reimusgamer@gmail.com",
    "password": "reimus12345",
    "avatar": "fgsadfjeuiru289j8f72u4f43rt3r43rf34fr43"
      },
"trader": {
        "businessName": "Cristo Rey",
        "vatCategory": "Mono tributista",
        "code": "25442345434",
        "grossIncome": "27-41.634.986-6"
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
