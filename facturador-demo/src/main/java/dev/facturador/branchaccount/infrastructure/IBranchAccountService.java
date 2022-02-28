package dev.facturador.branchaccount.infrastructure;

import dev.facturador.branchaccount.domain.CuentaSecundaria;

public interface IBranchAccountService {
    CuentaSecundaria findSecondaryAccountByUsername(String username);
}
