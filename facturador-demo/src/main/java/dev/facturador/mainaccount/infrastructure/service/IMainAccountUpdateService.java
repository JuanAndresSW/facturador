package dev.facturador.mainaccount.infrastructure.service;

import dev.facturador.mainaccount.domain.MainAccount;
import dev.facturador.mainaccount.domain.vo.agregate.UpdateRequest;

import java.util.Collection;
import java.util.Optional;

public interface IMainAccountUpdateService {
    void update(UpdateRequest tryUpdate, MainAccount account);
    MainAccount getMainAccountByUsername(String username);

    Boolean existsByUsernameOfUser(String username);
    Boolean existsByUniqueKeyOfTrader(String uniqueKey);

    String verifyUsernameNotExists(UpdateRequest tryUpdate);
    String verifyCuitNotExists(UpdateRequest tryUpdate);
    String verifyUsernameAndCodeNotExists(UpdateRequest tryUpdate);
    String verifyIfCotainsNewPassword(UpdateRequest tryUpdate, MainAccount user);
    String verifyNameAndCategoryAreDifferent(UpdateRequest data, MainAccount user);
    String verifyData(UpdateRequest data, MainAccount user);
}
