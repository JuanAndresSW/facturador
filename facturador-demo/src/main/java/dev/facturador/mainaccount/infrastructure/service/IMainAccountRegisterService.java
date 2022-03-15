package dev.facturador.mainaccount.infrastructure.service;

import dev.facturador.mainaccount.domain.vo.agregate.RegisterRequest;

import java.util.Collection;
import java.util.Optional;

public interface IMainAccountRegisterService {
    void register(RegisterRequest tryRegister);

    Optional<String> existsByUsernameOfUser(String username);
    Optional<String> existsByEmailOfUsuarios(String email);
    Optional<String> existsByUniqueKeyOfTrader(String uniqueKey);

    Collection<String> whenIndicesAreRepeatedReturnErrror(RegisterRequest account);
}
