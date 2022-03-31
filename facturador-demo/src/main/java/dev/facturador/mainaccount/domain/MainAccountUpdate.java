package dev.facturador.mainaccount.domain;

import dev.facturador.mainaccount.domain.exception.UserOrTraderIsNull;
import dev.facturador.trader.domain.TraderUpdate;
import dev.facturador.user.domain.UserUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class MainAccountUpdate {
    private UserUpdate userUpdate;
    private TraderUpdate traderUpdate;

    public static MainAccountUpdate starter(UserUpdate userUpdate, TraderUpdate traderUpdate) throws UserOrTraderIsNull {
        validateNotNull(userUpdate, traderUpdate);
        return new MainAccountUpdate(userUpdate, traderUpdate);
    }

    public static void validateNotNull(UserUpdate userUpdate, TraderUpdate traderUpdate) throws UserOrTraderIsNull {
        if (userUpdate == null) {
            throw new UserOrTraderIsNull("Usuario para el update es nulo");
        }
        if (traderUpdate == null) {
            throw new UserOrTraderIsNull("Comerciante para el update es nulo");
        }
    }
}
