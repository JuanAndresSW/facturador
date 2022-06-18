package dev.facturador.account.application.usecases;

import dev.facturador.account.domain.Account;
import dev.facturador.account.domain.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class GetAccountUseCase {
    @Autowired
    private AccountRepository repository;

    public Optional<Account> handleGetAccount(String username) {
        return repository.findByOwnerUserUsername(username);
    }
}
