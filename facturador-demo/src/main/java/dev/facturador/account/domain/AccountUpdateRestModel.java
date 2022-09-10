package dev.facturador.account.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.facturador.trader.domain.TraderUpdate;
import dev.facturador.user.domain.UserUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public final class AccountUpdateRestModel {
    @Valid
    @NotNull
    @JsonProperty("user")
    private UserUpdate userUpdate;

    @Valid
    @NotNull
    @JsonProperty("trader")
    private TraderUpdate traderUpdate;

    public static AccountUpdateRestModel valueOf(UserUpdate userUpdate, TraderUpdate traderUpdate) {
        return new AccountUpdateRestModel(userUpdate, traderUpdate);
    }

}
