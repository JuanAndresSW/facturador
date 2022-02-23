package dev.facturador.services.impl;

import dev.facturador.repository.IComercianteRepository;
import dev.facturador.services.ITraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TraderService implements ITraderService {

    @Autowired
    private IComercianteRepository repository;

    /**
     * Comprueba si ya existe el cuit/cuil en la base de datos
     */
    @Override
    public boolean existsByUniqueKey(String uniqueKey) {
        return repository.existsByUniqueKey(uniqueKey);
    }


}
