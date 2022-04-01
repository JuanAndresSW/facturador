package dev.facturador.mainaccount.application.usecases;

import dev.facturador.mainaccount.domain.MainAccount;
import dev.facturador.mainaccount.domain.MainAccountRepository;
import dev.facturador.mainaccount.domain.MainAccountUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class UpdateMainAccountUseCase {
    @Autowired
    private MainAccountRepository repository;

    public void handle(MainAccountUpdate request, MainAccount account) {
        var user = MainAccount.create(request, account);
        repository.saveAndFlush(user);
    }

    public MainAccount getMainAccountByUsername(String username) {
        return repository.findByUserMainAccountUsername(username);
    }


    public Boolean existsByUsernameOfUser(String username) {
        return Boolean.TRUE.equals(repository.existsByUserMainAccountUsername(username));
    }


    public Boolean existsByUniqueKeyOfTrader(String uniqueKey) {
        return Boolean.TRUE.equals(repository.existsByAccountOwnerUniqueKey(uniqueKey));
    }

    public String verifyUsernameNotExists(MainAccountUpdate tryUpdate) {
        if (existsByUsernameOfUser(tryUpdate.getUserUpdate().newUsername())) {
            if (existsByUniqueKeyOfTrader(tryUpdate.getTraderUpdate().newCode())) {
                return "Nombre de Usuario y Cuit ya se encuentran en uso";
            }
            return "Nombre de Usuario ya se encuentra en uso";
        }
        return null;
    }

    public String verifyCuitNotExists(MainAccountUpdate tryUpdate) {
        if (existsByUniqueKeyOfTrader(tryUpdate.getTraderUpdate().newCode())) {
            if (existsByUsernameOfUser(tryUpdate.getUserUpdate().newUsername())) {
                return "Nombre de Usuario y Cuit ya se encuentran en uso";
            }
            return "Cuit ya se encuentra en uso";
        }
        return null;
    }

    public String verifyUsernameAndCodeNotExists(MainAccountUpdate tryUpdate) {
        String message = null;
        if (StringUtils.hasText(tryUpdate.getUserUpdate().newUsername())) {
            message = verifyUsernameNotExists(tryUpdate);
        }
        if (!StringUtils.hasText(message)) {
            if (StringUtils.hasText(tryUpdate.getTraderUpdate().newCode())) {
                message = verifyCuitNotExists(tryUpdate);
            }
        }
        return message;
    }

    public String verifyIfCotainsNewPassword(MainAccountUpdate tryUpdate, MainAccount user) {
        if (StringUtils.hasText(tryUpdate.getUserUpdate().newPassword())) {
            var argon = new Argon2PasswordEncoder(16, 32, 1, 2048, 2);
            String passwordEncoded = user.getUserMainAccount().getPassword();
            String password = tryUpdate.getUserUpdate().password();

            if (!argon.matches(password, passwordEncoded)) {
                return "La contraseña antigua es incorrecta";
            }
        }
        return null;
    }

    public String verifyData(MainAccountUpdate data, MainAccount user) {
        String message = null;
        message = verifyIfCotainsNewPassword(data, user);
        if (StringUtils.hasText(message)) {
            return message;
        }
        message = verifyUsernameAndCodeNotExists(data);
        if (StringUtils.hasText(message)) {
            return message;
        }
        message = verifyNameAndCategoryAreDifferent(data, user);
        if (StringUtils.hasText(message)) {
            return message;
        }
        return null;
    }

    public String verifyNameAndCategoryAreDifferent(MainAccountUpdate data, MainAccount user) {
        if (StringUtils.hasText(data.getTraderUpdate().newBusinessName())) {
            String businessName = user.getAccountOwner().getName();
            if (businessName.equals(data.getTraderUpdate().newBusinessName())) {
                return "La informacion debe de ser distinta a la actual para actualizarla";
            }
        }
        if (StringUtils.hasText(data.getTraderUpdate().newVatCategory())) {
            String actualVat = user.getAccountOwner().getVat().name();
            String newVat = data.getTraderUpdate().newVatCategory();
            if (actualVat.toLowerCase().equals(newVat.toLowerCase())) {
                return "La informacion debe de ser distinta a la actual para actualizarla";
            }
        }
        return null;
    }
}
