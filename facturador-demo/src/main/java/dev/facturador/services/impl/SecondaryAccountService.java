package dev.facturador.services.impl;

import dev.facturador.entities.CuentaSecundaria;
import dev.facturador.repository.ICuentaSecundariaRepository;
import dev.facturador.services.ISecondaryAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SecondaryAccountService implements ISecondaryAccountService {

    @Autowired
    private ICuentaSecundariaRepository repository;

    /**
     * Busca si este username esta relacionado con una cuenta secundaria
     * @param username Username a comprobar
     * @return Devuelve una cuenta secundaria si existe
     */
    @Override
    public CuentaSecundaria findSecondaryAccountByUsername(String username) {
        Optional<CuentaSecundaria> secondaryAccount = repository.findByUsername(username);
        if(secondaryAccount.isEmpty()){
            return null;
        }
        return secondaryAccount.get();
    }
}
