package dev.facturador.mainaccount.application.usecases;

import dev.facturador.mainaccount.domain.MainAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteMainAccountUseCase {
    private MainAccountRepository repository;

    public void deleteByUsername(String username) {
        repository.deleteByUserMainAccountUsername(username);
    }

    public Boolean existsByUsername(String username) {
        return Boolean.TRUE.equals(repository.existsByUserMainAccountUsername(username));
    }
}
