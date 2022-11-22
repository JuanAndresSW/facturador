package dev.facturador.account.application;

import dev.facturador.account.domain.Account;
import dev.facturador.account.domain.AccountUpdateRestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Se encarga de actualizar la cuenta
 */
@Component
public class UpdateAccountService {
    @Autowired
    private ChecksAccountService checksUseCase;

    /**
     * Llama a todas las comprobaciones
     */
    public String allChecksToUpdate(AccountUpdateRestModel data, Account user) {
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

    /**
     * Verifica que el username y el CUIT o ambos no esten en uso
     */
    public String verifyUsernameAndCodeNotExists(AccountUpdateRestModel tryUpdate) {
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

    /**
     * Verifica que la contraseña antigua sea igual a la almacenada
     */
    public String checkOldPasswordIsTrue(AccountUpdateRestModel tryUpdate, Account user) {
        var argon = new Argon2PasswordEncoder(16, 32, 1, 2048, 2);
        String passwordEncoded = user.getOwnerUser().getPassword();
        String password = tryUpdate.getUserUpdate().password();
        if (!argon.matches(password, passwordEncoded)) {
            return "La contraseña antigua es incorrecta";
        }
        return null;
    }


    /**
     * Si quiere actualizar nombre de comerciante y categoria comprueba que no sean los mismos
     */
    public String verifyNameAndCategoryAreDifferent(AccountUpdateRestModel data, Account user) {
        if (StringUtils.hasText(data.getTraderUpdate().updatedBusinessName())) {
            String businessName = user.getOwnerTrader().getBusinessName();
            if (businessName.equals(data.getTraderUpdate().updatedBusinessName())) {
                return "La información debe de ser distinta a la actual para actualizarla";
            }
        }
        if (StringUtils.hasText(data.getTraderUpdate().updatedVatCategory())) {
            String actualVat = user.getOwnerTrader().getVatCategory().name();
            String updatedVat = data.getTraderUpdate().updatedVatCategory();
            if (actualVat.equalsIgnoreCase(updatedVat)) {
                return "La información debe de ser distinta a la actual para actualizarla";
            }
        }
        return null;
    }
}
