package dev.facturador.services.impl;

import dev.facturador.entities.CuentaPrincipal;
import dev.facturador.repository.ICuentaPrincipalRepository;
import dev.facturador.services.IMainAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MainAccountService implements IMainAccountService {

    @Autowired
    private ICuentaPrincipalRepository repository;

    /**
     * Salva una cuenta principal en la base de datos
     * (esta tambien guarda Usuario[Junto con el Avatar si existe] y Comerciante)
     */
    @Override
    public void register(CuentaPrincipal mainAccount) {
        repository.saveAndFlush(mainAccount);
    }

    /**
     * Devuelve una cuenta principal relacionada con X Username
     *
     * @param username Username que deberia estar relacionado
     */
    @Override
    public CuentaPrincipal getMainAccountByUsername(String username) {
        Optional<CuentaPrincipal> mainAccount = repository.findByUsername(username);
        if (mainAccount.isEmpty()) {
            return null;
        }
        return mainAccount.get();
    }
}
