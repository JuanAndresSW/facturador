package dev.facturador.account.application.usecases;

import dev.facturador.account.domain.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**Caso de uso llama al repositorio de Account y elimina la cuenta*/
@Service
@Transactional
public class DeleteAccountUseCase {
    @Autowired
    private AccountRepository repository;

    public void handleAccountDelete(String username) {
        //repository.delete(new Account(new User(username)));
        repository.deleteByOwnerUserUsername(username);
    }
}