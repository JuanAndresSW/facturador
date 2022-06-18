package dev.facturador.account.application.usecases;

import dev.facturador.account.domain.Account;
import dev.facturador.account.domain.AccountRepository;
import dev.facturador.account.domain.AccountUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class UpdateAccountUseCase {
    @Autowired
    private AccountRepository repository;
    @Autowired
    private ChecksAccountUseCase checksUseCase;

    public void handleAccountUpdate(AccountUpdate request, Account account) {
        var user = Account.create(request, account);
        repository.saveAndFlush(user);
    }

    public String verifyUsernameAndCodeNotExists(AccountUpdate tryUpdate) {
        String message = null;
        if (StringUtils.hasText(tryUpdate.getUserUpdate().updatedUsername()) && StringUtils.hasText(tryUpdate.getTraderUpdate().updatedCuit())) {
            message = this.checksUseCase.errorIfCuitOrUsernameIsInUse(tryUpdate.getUserUpdate().updatedUsername(), tryUpdate.getTraderUpdate().updatedCuit());
        }
        if (!StringUtils.hasText(message)) {
            if (StringUtils.hasText(tryUpdate.getTraderUpdate().updatedCuit())) {
                message = this.checksUseCase.errorIfCuitIsInUse(tryUpdate.getTraderUpdate().updatedCuit());
            }
            if (StringUtils.hasText(tryUpdate.getUserUpdate().updatedUsername())) {
                message = this.checksUseCase.errorIfUsernameIsInUse(tryUpdate.getUserUpdate().updatedUsername());
            }
        }
        return message;
    }

    public String checkOldPasswordIsTrue(AccountUpdate tryUpdate, Account user) {
        var argon = new Argon2PasswordEncoder(16, 32, 1, 2048, 2);
        String passwordEncoded = user.getOwnerUser().getPassword();
        String password = tryUpdate.getUserUpdate().password();
        if (!argon.matches(password, passwordEncoded)) {
            return "La contraseña antigua es incorrecta";
        }
        return null;
    }

    public String allChecksToUpdate(AccountUpdate data, Account user) {
        String message = null;
        if (StringUtils.hasText(data.getUserUpdate().updatedPassword())) {
            message = checkOldPasswordIsTrue(data, user);
        }
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

    public String verifyNameAndCategoryAreDifferent(AccountUpdate data, Account user) {
        if (StringUtils.hasText(data.getTraderUpdate().updatedBusinessName())) {
            String businessName = user.getOwnerTrader().getName();
            if (businessName.equals(data.getTraderUpdate().updatedBusinessName())) {
                return "La informacion debe de ser distinta a la actual para actualizarla";
            }
        }
        if (StringUtils.hasText(data.getTraderUpdate().updatedVatCategory())) {
            String actualVat = user.getOwnerTrader().getVat().name();
            String updatedVat = data.getTraderUpdate().updatedVatCategory();
            if (actualVat.equalsIgnoreCase(updatedVat)) {
                return "La informacion debe de ser distinta a la actual para actualizarla";
            }
        }
        return null;
    }
}
