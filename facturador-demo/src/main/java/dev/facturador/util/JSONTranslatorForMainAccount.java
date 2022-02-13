package dev.facturador.util;

import dev.facturador.dto.RegisterDto;
import dev.facturador.entities.AvatarUsuario;
import dev.facturador.entities.Comerciante;
import dev.facturador.entities.CuentaPrincipal;
import dev.facturador.entities.Usuarios;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class JSONTranslatorForMainAccount {

    /**
     * Traduce una un dto a una cuenta principal
     * @return retorna una cuenta principal
     */
    public static CuentaPrincipal translatorAccountToMainAccount(RegisterDto account){
        var mainAccount = new CuentaPrincipal();
        mainAccount.setAccountOwner(new Comerciante
                (account.getTraderDto().getCode(), account.getTraderDto().getVatCategory(), account.getTraderDto().getBusinessName()));
        mainAccount.getAccountOwner().setGrossIncome(account.getTraderDto().getGrossIncome());
        mainAccount.setUserMainAccount(new Usuarios
                (account.getUserDto().getUsername(), account.getUserDto().getPassword(), account.getUserDto().getEmail()));
        mainAccount.getUserMainAccount().setAvatarUser(new AvatarUsuario(account.getUserDto().getAvatar(), mainAccount.getUserMainAccount()));
        return mainAccount;
    }

    /**
     * Hace el hash de la contraseña
     * @param account Dto se traduce a una cuenta principal con el metodo translatorAccountToMainAccount
     * @return retorna la cuenta principal con el hash echo
     */
    public static CuentaPrincipal mainAccountPrepareForSave(RegisterDto account){
        var mainAccount = translatorAccountToMainAccount(account);
        var argon2 = new Argon2PasswordEncoder(16, 32, 1, 2048, 2);
        mainAccount.getUserMainAccount().setPassword(argon2.encode(mainAccount.getUserMainAccount().getPassword()));
        return mainAccount;
    }
}
