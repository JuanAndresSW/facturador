package dev.facturador.mainaccount.infrastructure;

import dev.facturador.mainaccount.domain.MainAccount;
import dev.facturador.mainaccount.domain.bo.RegisterRequest;

import java.util.Optional;

public interface IMainAccountService {
    void register(RegisterRequest tryRegister);

    Optional<String> existsByUsernameOfUsuarios(String username);

    Optional<String> existsByEmailOfUsuarios(String email);

    Optional<String> existsByUniqueKeyOfTrader(String uniqueKey);

    String whenIndicesAreRepeatedReturnErrror(RegisterRequest account);


}
