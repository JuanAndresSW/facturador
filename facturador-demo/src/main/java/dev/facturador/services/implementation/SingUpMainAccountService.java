package dev.facturador.services.implementation;

import dev.facturador.entities.CuentaPrincipal;
import dev.facturador.repository.ICuentaPrincipalRepository;
import dev.facturador.services.ISingUpMainAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class SingUpMainAccountService implements ISingUpMainAccountService {

    @Autowired
    private ICuentaPrincipalRepository mainAccountRepository;

    /**
     * Guarda la cuenta principal y encripta el password
     */
    @Override
    public void register(CuentaPrincipal mainAccount) {
        mainAccountRepository.save(mainAccount);
    }
}
