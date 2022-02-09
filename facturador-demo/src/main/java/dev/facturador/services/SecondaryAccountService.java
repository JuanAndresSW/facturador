package dev.facturador.services;

import dev.facturador.entities.CuentaSecundaria;
import dev.facturador.repository.ICuentaSecundariaRepository;
import dev.facturador.services.abstracciones.ISecondaryAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class SecondaryAccountService implements ISecondaryAccountService {

    @Autowired
    private ICuentaSecundariaRepository repository;

    @Override
    public CuentaSecundaria getSecondaryAccountByUsername(String username) {
        Optional<CuentaSecundaria> secondaryAccount = repository.findByUsername(username);
        if(secondaryAccount.isEmpty()){
            return null;
        }
        return secondaryAccount.get();
    }
}
