package dev.facturador.services;

import dev.facturador.entities.CuentaPrincipal;
import dev.facturador.repository.ICuentaPrincipalRepository;
import dev.facturador.services.abstracciones.IMainAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MainAccountService implements IMainAccountService {

    @Autowired
    private ICuentaPrincipalRepository repository;

    @Override
    public CuentaPrincipal getMainAccountByUsername(String username) {
        Optional<CuentaPrincipal> mainAccount = repository.findByUsername(username);
        if(mainAccount.isEmpty()){
            return null;
        }
        return mainAccount.get();
    }
}
