package dev.facturador.util;

import dev.facturador.dto.LoginDto;
import dev.facturador.entities.Usuarios;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Traduce dto Account a Main Account, y realiza el hash de la contraseña
 */
@Component
public final class JSONTranslatorForUsers {

    /**
     * Traduce dto RegisterDto a MainAccount
     */
    public static Usuarios translatorUserToDetailsAccount(LoginDto userDto){
        var user = new Usuarios();
        if(!userDto.getUsernameOrEmail().contains("@")){
            user.setUsername(userDto.getUsernameOrEmail());
        }
        if(userDto.getUsernameOrEmail().contains("@")){
            user.setEmail(userDto.getUsernameOrEmail());
        }
        user.setPassword(userDto.getPassword());

        return user;
    }

    /**
     * Hace el hash de la contraseña
     */
    public static Usuarios prepareForCheks(LoginDto userDto){
        var user = translatorUserToDetailsAccount(userDto);
        //Parametros de Argon2
        // (SaltLength: 16 Bytes, HashLength: 32 Bytes, Paralelismo: 1 solo hilo,
        // Memoria: 2048 Kilobytes, Iteraciones 2)
        Argon2PasswordEncoder argon2 = new Argon2PasswordEncoder(16, 32, 1, 2048, 2);
        user.setPassword(argon2.encode(user.getPassword()));
        return user;
    }
}
