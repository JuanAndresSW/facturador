package dev.facturador.services.abstracciones;

import dev.facturador.dto.LoginDto;
import dev.facturador.entities.CuentaPrincipal;
import dev.facturador.entities.CuentaSecundaria;
import dev.facturador.entities.Usuarios;

public interface IUserService {

    Usuarios getUserByUsername(Usuarios username);
    Usuarios getUserWithCrdentials(LoginDto user);
    Usuarios getUserForToken(Usuarios user);
    CuentaPrincipal getMainAccountPertainToUser(String username);
    CuentaSecundaria getSecondaryAccountPertainToUserByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
