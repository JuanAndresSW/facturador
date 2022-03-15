package dev.facturador.mainaccount.application;

import dev.facturador.mainaccount.domain.MainAccount;
import dev.facturador.mainaccount.domain.MainAccountRepository;
import dev.facturador.mainaccount.infrastructure.service.IMainAccountRecoverInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class  MainAccountRecoverInfoService implements IMainAccountRecoverInfoService {
    @Autowired
    private MainAccountRepository repository;


    @Override
    public Optional<MainAccount> getMainAccountByUsername(String username) {
        return repository.findByUserMainAccountUsername(username);
    }
}
