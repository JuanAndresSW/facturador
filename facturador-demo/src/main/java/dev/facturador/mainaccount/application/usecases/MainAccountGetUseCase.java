package dev.facturador.mainaccount.application.usecases;

import dev.facturador.mainaccount.domain.MainAccount;
import dev.facturador.mainaccount.domain.MainAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MainAccountGetUseCase {
    @Autowired
    private MainAccountRepository repository;

    public MainAccount handle(String username) {
        return repository.findByUserMainAccountUsername(username);
    }
}
