package dev.facturador.services.abstracciones;

import dev.facturador.entities.CuentaSecundaria;

public interface ISecondaryAccountService {
    CuentaSecundaria getSecondaryAccountByUsername(String username);
}
