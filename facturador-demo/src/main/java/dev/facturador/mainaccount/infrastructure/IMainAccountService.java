package dev.facturador.mainaccount.infrastructure;

import dev.facturador.mainaccount.domain.CuentaPrincipal;
import dev.facturador.mainaccount.domain.bo.RegisterBo;

import java.util.Optional;

public interface IMainAccountService {
    CuentaPrincipal getMainAccountByUsername(String username);

    void register(RegisterBo tryRegister);

    Optional<String> existsByUsernameOfUsuarios(String username);
    Optional<String>  existsByEmailOfUsuarios(String email);
    Optional<String>  existsByUniqueKeyOfTrader(String uniqueKey);
    String whenIndicesAreRepeatedReturnErrror(RegisterBo account);


}
