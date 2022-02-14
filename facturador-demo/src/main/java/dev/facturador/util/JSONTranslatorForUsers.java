package dev.facturador.util;

import dev.facturador.dto.LoginDto;
import dev.facturador.entities.Usuarios;
import org.springframework.stereotype.Component;


@Component
public class JSONTranslatorForUsers {

    /**
     * Traudce el dto a nuestro tipo e usuario comprueba si se trajo un username o un email
     * @param userDto Es el dto del login
     * @return
     */
    public static Usuarios translatorUserToDetailsAccount(LoginDto userDto){
        var user = new Usuarios();
        if(!userDto.getUsernameOrEmail().contains("@")){
            if(!userDto.getUsernameOrEmail().contains(".")){
                //Si llegue hasta aqui no tiene ni @ ni .
                user.setUsername(userDto.getUsernameOrEmail());
            }
        }
        if(userDto.getUsernameOrEmail().contains("@")){
            user.setEmail(userDto.getUsernameOrEmail());
        }
        user.setPassword(userDto.getPassword());
        return user;
    }

}
