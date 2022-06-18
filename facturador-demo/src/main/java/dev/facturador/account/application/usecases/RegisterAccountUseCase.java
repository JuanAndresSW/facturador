package dev.facturador.account.application.usecases;

import dev.facturador.account.domain.Account;
import dev.facturador.account.domain.AccountRegister;
import dev.facturador.account.domain.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegisterAccountUseCase {
    @Autowired
    private AccountRepository repository;

    public void handleAccountRegister(AccountRegister request) {
        var mainAccountLogged = Account.create(request);
        repository.save(mainAccountLogged);
    }
}
