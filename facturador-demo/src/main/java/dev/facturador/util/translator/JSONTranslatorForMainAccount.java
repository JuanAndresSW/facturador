package dev.facturador.util.translator;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import dev.facturador.dto.RegisterDto;
import dev.facturador.entities.AvatarUsuario;
import dev.facturador.entities.Comerciante;
import dev.facturador.entities.CuentaPrincipal;
import dev.facturador.entities.Usuarios;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * Traduce dto a Entity
 */
@Component
@Slf4j
public final class JSONTranslatorForMainAccount {

    /**
     * Transforma el dto RegisterDto a la entidad CuentaPrincipal
     */
    public static CuentaPrincipal translatorAccountToMainAccount(RegisterDto account){
        CuentaPrincipal mainAccount = new CuentaPrincipal();
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
        CuentaPrincipal mainAccount = translatorAccountToMainAccount(account);
        Argon2 argon = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        mainAccount.getUserMainAccount().setPassword(argon.hash(2, 1024, 1, mainAccount.getUserMainAccount().getPassword().toCharArray(), StandardCharsets.UTF_8));
        return mainAccount;
    }

}
