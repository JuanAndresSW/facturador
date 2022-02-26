package dev.facturador.services;

import dev.facturador.entities.CuentaPrincipal;

public interface IMainAccountService {
    CuentaPrincipal getMainAccountByUsername(String username);

    void register(CuentaPrincipal mainAccount);

    Boolean existsByUsernameOfUsuarios(String username);

    Boolean existsByEmailOfUsuarios(String email);

    Boolean existsByUniqueKeyOfTrader(String uniqueKey);
}
