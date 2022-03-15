package dev.facturador.mainaccount.infrastructure.service;

import dev.facturador.mainaccount.domain.MainAccount;

import java.util.Optional;

public interface IMainAccountRecoverInfoService {

    Optional<MainAccount> getMainAccountByUsername(String username);
}
