package dev.facturador.services.impl;

import dev.facturador.entities.CuentaPrincipal;
import dev.facturador.repository.ICuentaPrincipalRepository;
import dev.facturador.services.IMainAccountService;
import lombok.RequiredArgsConstructor;
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
     * Registra una cuenta principal en la base de datos
     * Con la persistencia ne cascada se guarda las entidades:
     * Cuenta Principal, Comerciante, Usuario y Avatar Usuario(si existe)
     * @param mainAccount Cuenta principal a guardar
     */
    @Override
    public void register(CuentaPrincipal mainAccount) {
        repository.save(mainAccount);
    }

    /**
     * Busca si este username esta relacionado con una cuenta principal
     * @param username Username a comprobar
     * @return Devuelve una cuenta principal si existe
     */
    @Override
    public CuentaPrincipal getMainAccountByUsername(String username) {
        Optional<CuentaPrincipal> mainAccount = repository.findByUsername(username);
        if(mainAccount.isEmpty()){
            return null;
        }
        return mainAccount.get();
    }
}
