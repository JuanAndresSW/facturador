package dev.facturador.mainaccount.domain.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Dto para cuando se registra
 */
@Data
public final class RegisterBo {

    //Anotaciones para validar el modelo (Ejemplo NotNull dice que no puede recibir un nulo)
    @JsonProperty("user")
    @NotNull
    @Valid
    private UserBo userBo;
    @JsonProperty("trader")
    @NotNull
    @Valid
    private TraderBo traderBo;
}
