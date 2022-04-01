package dev.facturador.mainaccount.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.facturador.trader.domain.TraderRegister;
import dev.facturador.user.domain.UserRegister;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public record MainAccountRegister(@Valid @NotNull @JsonProperty("user") UserRegister userRegister,
                                  @Valid @NotNull @JsonProperty("trader") TraderRegister traderRegister) {

    public static MainAccountRegister starter(UserRegister userRegister, TraderRegister traderRegister) {
        return new MainAccountRegister(userRegister, traderRegister);
    }
}
