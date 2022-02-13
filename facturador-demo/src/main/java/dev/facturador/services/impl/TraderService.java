package dev.facturador.services.impl;

import dev.facturador.repository.IComercianteRepository;
import dev.facturador.services.ITraderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TraderService implements ITraderService {

    @Autowired
    private IComercianteRepository repository;

    @Override
    public boolean existsByCode(String uniqueKey) {
        return repository.existsByUniqueKey(uniqueKey);
    }
}
