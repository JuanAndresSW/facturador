package dev.facturador.branchaccount.application;

import dev.facturador.branchaccount.domain.BranchAccount;
import dev.facturador.branchaccount.domain.IBranchAccountRepository;
import dev.facturador.branchaccount.infrastructure.IBranchAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class BranchAccountService implements IBranchAccountService {

    @Autowired
    private IBranchAccountRepository repository;

    /**
     * Devuelve una cuenta secundaria relacionada con X Username
     *
     * @param username Username que deberia estar relacionado
     */
    @Override
    public BranchAccount findSecondaryAccountByUsername(String username) {
        Optional<BranchAccount> secondaryAccount = repository.findByUsername(username);
        if (secondaryAccount.isEmpty()) {
            return null;
        }
        return secondaryAccount.get();
    }
}
