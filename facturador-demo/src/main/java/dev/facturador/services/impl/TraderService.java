package dev.facturador.services.impl;

import dev.facturador.dto.ErrorDetailsDto;
import dev.facturador.dto.RegisterDto;
import dev.facturador.repository.IComercianteRepository;
import dev.facturador.services.ITraderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class TraderService implements ITraderService {

    @Autowired
    private IComercianteRepository repository;

    @Override
    public boolean existsByUniqueKey(String uniqueKey) {
        return repository.existsByUniqueKey(uniqueKey);
    }


}
