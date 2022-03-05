package dev.facturador.trader.application;

import dev.facturador.trader.domain.ITraderRepository;
import dev.facturador.trader.infrastructure.ITraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TraderService implements ITraderService {

    @Autowired
    private ITraderRepository repository;

    /**
     * Comprueba si ya existe el cuit/cuil en la base de datos
     */
    @Override
    public boolean isExistsTraderByUniquekey(String uniqueKey) {
        return repository.existsByUniqueKey(uniqueKey);
    }


}
