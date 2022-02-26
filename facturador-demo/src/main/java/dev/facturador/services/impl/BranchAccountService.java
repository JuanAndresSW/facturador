package dev.facturador.services.impl;

import dev.facturador.entities.CuentaSecundaria;
import dev.facturador.repository.IBranchAccountRepository;
import dev.facturador.services.IBranchAccountService;
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
    public CuentaSecundaria findSecondaryAccountByUsername(String username) {
        Optional<CuentaSecundaria> secondaryAccount = repository.findByUsername(username);
        if (secondaryAccount.isEmpty()) {
            return null;
        }
        return secondaryAccount.get();
    }
}
