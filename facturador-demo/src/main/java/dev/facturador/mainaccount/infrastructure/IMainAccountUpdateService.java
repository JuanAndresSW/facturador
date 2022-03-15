package dev.facturador.mainaccount.infrastructure;

import dev.facturador.mainaccount.domain.MainAccount;
import dev.facturador.mainaccount.domain.vo.agregate.UpdateRequest;

import java.util.Collection;
import java.util.Optional;

public interface IMainAccountUpdateService {
    void update(UpdateRequest tryUpdate, MainAccount account);

    Optional<String> existsByUsernameOfUser(String username);
    Optional<String> existsByUniqueKeyOfTrader(String uniqueKey);

    Collection<String> verifyUsernameAndCodeNotExists(UpdateRequest tryUpdate);

    MainAccount getMainAccountByUsername(String username);

    MainAccount verifyIfCotainsNewPassword(UpdateRequest tryUpdate);
}
