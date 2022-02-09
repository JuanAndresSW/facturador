package dev.facturador.services.abstracciones;

import dev.facturador.entities.CuentaPrincipal;

public interface IMainAccountService {
    CuentaPrincipal getMainAccountByUsername(String username);
}
