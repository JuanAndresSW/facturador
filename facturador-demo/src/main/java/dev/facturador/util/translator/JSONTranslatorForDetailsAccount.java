package dev.facturador.util.translator;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import dev.facturador.dto.LoginDto;
import dev.facturador.entities.Usuarios;
import org.springframework.stereotype.Component;

/**
 * Traduce dto  a Entity
 */
@Component
public final class JSONTranslatorForDetailsAccount {

    /**
     * Traduce dto RegisterDto a MainAccount
     */
    public static Usuarios translatorUserToDetailsAccount(LoginDto user){
        var details = new Usuarios();
        if(!user.getUsernameOrEmail().contains("@")){
            details.setUsername(user.getUsernameOrEmail());
        }
        if(user.getUsernameOrEmail().contains("@")){
            details.setEmail(user.getUsernameOrEmail());
        }
        details.setPassword(user.getPassword());

        return details;
    }

    /**
     * Hace el hash de la contraseña
     */
    public static Usuarios prepareForCheks(LoginDto user){
        var details = translatorUserToDetailsAccount(user);
        Argon2 argon = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        details.setPassword(argon.hash(2, 1024, 2, details.getPassword().getBytes()));
        return details;
    }
}
