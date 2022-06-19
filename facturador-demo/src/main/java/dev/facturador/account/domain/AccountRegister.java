package dev.facturador.account.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.facturador.trader.domain.TraderRegister;
import dev.facturador.user.domain.UserRegister;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public record AccountRegister(@Valid @NotNull @JsonProperty("user") UserRegister userRegister,
                              @Valid @NotNull @JsonProperty("trader") TraderRegister traderRegister) {

    public static AccountRegister valueOf(UserRegister userRegister, TraderRegister traderRegister) {
        return new AccountRegister(userRegister, traderRegister);
    }
}
