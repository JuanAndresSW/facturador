package dev.facturador.services;

import dev.facturador.entities.CuentaSecundaria;

public interface ISecondaryAccountService {
    CuentaSecundaria findSecondaryAccountByUsername(String username);
}
