package dev.facturador.mainaccount.application.usecases;

import dev.facturador.mainaccount.domain.MainAccount;
import dev.facturador.mainaccount.domain.MainAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MainAccountGetUseCase {
    private MainAccountRepository repository;

    public MainAccount handle(String username) {
        return repository.findByUserMainAccountUsername(username);
    }
}
