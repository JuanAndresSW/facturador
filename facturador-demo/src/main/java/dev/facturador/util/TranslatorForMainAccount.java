package dev.facturador.util;

import dev.facturador.dto.RegisterDto;
import dev.facturador.entities.AvatarUsuario;
import dev.facturador.entities.Comerciante;
import dev.facturador.entities.CuentaPrincipal;
import dev.facturador.entities.Usuarios;
import dev.facturador.entities.enums.Vat;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TranslatorForMainAccount {

    /**
     * Traduce una un dto a una cuenta principal
     *
     * @return retorna una cuenta principal
     */
    public static CuentaPrincipal translatorAccountToMainAccount(RegisterDto account) {
        var mainAccount = new CuentaPrincipal();

        mainAccount.setAccountOwner(new Comerciante(account.getTraderDto().code(), account.getTraderDto().grossIncome(), account.getTraderDto().businessName()));
        mainAccount.setUserMainAccount(new Usuarios(account.getUserDto().username(), account.getUserDto().password(), account.getUserDto().email()));

        mainAccount.getAccountOwner().setVat(defineVat(account));
        mainAccount.getUserMainAccount().setAvatarUser(defineIfHaveAvatar(account, mainAccount.getUserMainAccount()));
        mainAccount.getAccountOwner().setActive(0);
        mainAccount.getAccountOwner().setPassive(0);
        mainAccount.setCreateDate(LocalDateTime.now());

        return mainAccount;
    }

    /**
     * Hace el hash de la contraseña
     *
     * @param account Dto se traduce a una cuenta principal con el metodo translatorAccountToMainAccount
     * @return retorna la cuenta principal con el hash echo
     */
    public static CuentaPrincipal mainAccountPrepareForSave(RegisterDto account) {
        var mainAccount = translatorAccountToMainAccount(account);
        var argon2 = new Argon2PasswordEncoder(16, 32, 1, 2048, 2);
        mainAccount.getUserMainAccount().setPassword(argon2.encode(mainAccount.getUserMainAccount().getPassword()));
        return mainAccount;
    }

    /**
     * Logica para definir cual es el IVA
     *
     * @param account Comprueba cual es el calor que contiene
     * @return retorna un Vat enum
     */
    private static Vat defineVat(RegisterDto account) {
        if (account.getTraderDto().vatCategory().contains("Responsable")) {
            return Vat.RESPONSABLE_INSCRIPTO;
        }
        if (account.getTraderDto().vatCategory().contains("Monotributista")) {
            return Vat.MONOTRIBUTISTA;
        }
        if (account.getTraderDto().vatCategory().contains("Sujeto")) {
            return Vat.SUJETO_EXENTO;
        }
        return null;
    }

    /**
     * Logica para decidir si tiene avatar o no tiene
     *
     * @param account Comprueba que tenga
     * @param user    Usuario dueño del avatar si es que existe
     * @return retorna la asignacion del avatar
     */
    private static AvatarUsuario defineIfHaveAvatar(RegisterDto account, Usuarios user) {
        if (!StringUtils.hasText(account.getUserDto().avatar())) {
            return null;
        }
        return new AvatarUsuario(account.getUserDto().avatar(), user);
    }
}
