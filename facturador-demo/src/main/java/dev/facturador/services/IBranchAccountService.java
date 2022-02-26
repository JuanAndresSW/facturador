package dev.facturador.services;

import dev.facturador.entities.CuentaSecundaria;

public interface IBranchAccountService {
    CuentaSecundaria findSecondaryAccountByUsername(String username);
}
