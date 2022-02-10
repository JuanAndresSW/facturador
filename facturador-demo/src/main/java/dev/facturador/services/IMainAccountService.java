package dev.facturador.services;

import dev.facturador.entities.CuentaPrincipal;

public interface IMainAccountService {
    CuentaPrincipal getMainAccountByUsername(String username);
}
