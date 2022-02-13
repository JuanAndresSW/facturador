package dev.facturador.services.impl;

import dev.facturador.entities.CuentaPrincipal;
import dev.facturador.repository.ICuentaPrincipalRepository;
import dev.facturador.services.IUserService;
import dev.facturador.services.ISignUpMainAccountService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
//Indica que la clase tiene metodos transaccionales
@Transactional
@NoArgsConstructor
public class SignUpMainAccountService implements ISignUpMainAccountService {

    @Autowired
    private ICuentaPrincipalRepository mainAccountRepository;
    @Autowired
    private IUserService detailsService;

    /**
     * Registra la cuenta principal
     */
    @Override
    public void register(CuentaPrincipal mainAccount) {
         mainAccountRepository.save(mainAccount);
    }


}
