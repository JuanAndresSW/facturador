package dev.facturador.services.component;

import org.springframework.stereotype.Component;

/**
 * Sobre escritura de la clase BCryptPasswordEncoder
 */
@Component("bCryptPasswordEncoder")
public final class CustomBCryptPasswordEncoder extends org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder{

    public CustomBCryptPasswordEncoder(){
        super();
    }
}
