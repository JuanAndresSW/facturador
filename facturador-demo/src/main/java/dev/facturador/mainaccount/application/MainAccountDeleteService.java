package dev.facturador.mainaccount.application;

import dev.facturador.mainaccount.domain.MainAccountRepository;
import dev.facturador.mainaccount.infrastructure.service.IMainAccountDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MainAccountDeleteService implements IMainAccountDeleteService {
    @Autowired
    private MainAccountRepository repository;

    @Override
    public void deleteByUsername(String username) {
        repository.deleteByUserMainAccountUsername(username);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return Boolean.TRUE.equals(repository.existsByUserMainAccountUsername(username));
    }
}
