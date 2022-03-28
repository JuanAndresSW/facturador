package dev.facturador.mainaccount.infrastructure.service;

import dev.facturador.mainaccount.domain.vo.agregate.RegisterRequest;

import java.util.Collection;
import java.util.Optional;

public interface IMainAccountRegisterService {
    void register(RegisterRequest tryRegister);

    Boolean existsByUsernameOfUser(String username);
    Boolean existsByEmailOfUsuarios(String email);
    Boolean existsByUniqueKeyOfTrader(String uniqueKey);

    String whenIndicesAreRepeatedReturnErrror(RegisterRequest account);
}
