package dev.facturador.services;

import dev.facturador.entities.CuentaSecundaria;

public interface ISecondaryAccountService {
    CuentaSecundaria getSecondaryAccountByUsername(String username);
}
