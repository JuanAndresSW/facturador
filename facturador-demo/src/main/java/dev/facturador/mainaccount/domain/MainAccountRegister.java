package dev.facturador.mainaccount.domain;

import dev.facturador.mainaccount.domain.exception.UserOrTraderIsNull;
import dev.facturador.trader.domain.TraderRegister;
import dev.facturador.user.domain.UserRegister;

public record MainAccountRegister(UserRegister userRegister, TraderRegister traderRegister) {

    public static MainAccountRegister starter(UserRegister userRegister, TraderRegister traderRegister)
            throws UserOrTraderIsNull {
        validateNotNull(userRegister, traderRegister);
        return new MainAccountRegister(userRegister, traderRegister);
    }

    public static void validateNotNull(UserRegister userRegister, TraderRegister traderRegister) throws UserOrTraderIsNull {
        if (userRegister == null) {
            throw new UserOrTraderIsNull("Usuario para el registro es nulo");
        }
        if (traderRegister == null) {
            throw new UserOrTraderIsNull("Comerciante para el registro es nulo");
        }
    }
}

