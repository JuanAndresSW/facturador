package dev.facturador.branchaccount.infrastructure;

import dev.facturador.branchaccount.domain.BranchAccount;

public interface IBranchAccountService {
    BranchAccount findSecondaryAccountByUsername(String username);
}
