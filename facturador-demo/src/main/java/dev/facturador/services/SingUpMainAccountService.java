package dev.facturador.services;

import dev.facturador.entities.CuentaPrincipal;
import dev.facturador.repository.ICuentaPrincipalRepository;
import dev.facturador.services.abstracciones.IUserService;
import dev.facturador.services.abstracciones.ISingUpMainAccountService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
//Indica que la clase tiene metodos transaccionales
@Transactional
@NoArgsConstructor
public class SingUpMainAccountService implements ISingUpMainAccountService {

    @Autowired
    private ICuentaPrincipalRepository mainAccountRepository;
    @Autowired
    private IUserService detailsService;

    /**
     * Guarda la cuenta principal y encripta el password
     */
    @Override
    public void register(CuentaPrincipal mainAccount) {
         mainAccountRepository.save(mainAccount);
    }


}
