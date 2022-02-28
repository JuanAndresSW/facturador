package dev.facturador.services.impl;

import dev.facturador.entities.CuentaPrincipal;
import dev.facturador.repository.IMainAccountRepository;
import dev.facturador.services.IMainAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MainAccountService implements IMainAccountService {

    @Autowired
    private IMainAccountRepository repository;

    /**
     * Guarda en la base de datos
     *
     * @param mainAccount {@link CuentaPrincipal} Es la entidad
     */
    @Override
    public void register(CuentaPrincipal mainAccount) {
        repository.save(mainAccount);
    }

    /**
     * Recupera una {@link CuentaPrincipal} con el username proporcionado <br/>
     * Utilizando la clase {@link Optional} para asegurar los {@link NullPointerException}
     *
     * @param username Referencia para encontrar la {@link CuentaPrincipal}
     * @return devuelve un {@link Optional} de {@link CuentaPrincipal}
     */
    @Override
    public CuentaPrincipal getMainAccountByUsername(String username) {
        Optional<CuentaPrincipal> mainAccount = repository.findByUsername(username);
        if (mainAccount.isEmpty()) {
            return null;
        }
        return mainAccount.get();
    }

    @Override
    public Boolean existsByUsernameOfUsuarios(String username) {
        return repository.existsByUserMainAccountUsername(username);
    }

    @Override
    public Boolean existsByEmailOfUsuarios(String email) {
        return repository.existsByUserMainAccountEmail(email);
    }

    @Override
    public Boolean existsByUniqueKeyOfTrader(String uniqueKey) {
        return repository.existsByAccountOwnerUniqueKey(uniqueKey);
    }
}
