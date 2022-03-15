package dev.facturador.mainaccount.infrastructure;

import dev.facturador.mainaccount.domain.MainAccount;
import dev.facturador.mainaccount.domain.vo.agregate.RegisterRequest;
import dev.facturador.mainaccount.domain.vo.agregate.UpdateRequest;

public interface IFactoryMainAccount {
    MainAccount createMainAccountForRegister(RegisterRequest tryRegister);

    String hashPassword(String password);

    MainAccount createMainAccountForUpdate(UpdateRequest request, MainAccount account);
}
