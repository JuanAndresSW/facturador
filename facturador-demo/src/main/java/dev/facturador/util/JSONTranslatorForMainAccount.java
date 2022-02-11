package dev.facturador.util;

import dev.facturador.dto.RegisterDto;
import dev.facturador.entities.AvatarUsuario;
import dev.facturador.entities.Comerciante;
import dev.facturador.entities.CuentaPrincipal;
import dev.facturador.entities.Usuarios;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Traduce dto a Entity
 */
@Component
public class JSONTranslatorForMainAccount {

    /**
     * Transforma el dto RegisterDto a la entidad CuentaPrincipal
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
     */
    public static CuentaPrincipal mainAccountPrepareForSave(RegisterDto account){
        var mainAccount = translatorAccountToMainAccount(account);
        //Parametros de Argon2
        // (SaltLength: 16 Bytes, HashLength: 32 Bytes, Paralelismo: 1 solo hilo,
        // Memoria: 2048 Kilobytes, Iteraciones 2)
        var argon2 = new Argon2PasswordEncoder(16, 32, 1, 2048, 2);
        mainAccount.getUserMainAccount().setPassword(argon2.encode(mainAccount.getUserMainAccount().getPassword()));
        return mainAccount;
    }
}
