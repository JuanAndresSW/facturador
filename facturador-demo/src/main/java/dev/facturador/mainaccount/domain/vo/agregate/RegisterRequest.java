package dev.facturador.mainaccount.domain.vo.agregate;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.facturador.mainaccount.domain.vo.TraderRegister;
import dev.facturador.mainaccount.domain.vo.UserRegister;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Dto para cuando se registra
 */
@Data
public final class RegisterRequest {
    @JsonProperty("user")
    @NotNull
    @Valid
    private UserRegister userRegister;

    @JsonProperty("trader")
    @NotNull
    @Valid
    private TraderRegister traderRegister;
}

