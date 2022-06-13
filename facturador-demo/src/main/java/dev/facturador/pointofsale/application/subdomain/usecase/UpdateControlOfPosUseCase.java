package dev.facturador.pointofsale.application.subdomain.usecase;

import dev.facturador.pointofsale.domain.subdomain.ControlOfPointOfSale;
import dev.facturador.pointofsale.domain.subdomain.ControlOfPointOfSaleRepository;
import dev.facturador.pointofsale.domain.subdomain.ControlOfPosData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UpdateControlOfPosUseCase {
    @Autowired
    private ControlOfPointOfSaleRepository repository;

    public void handle(ControlOfPosData data) throws Exception {
        log.info("Data is: {}", data);
        repository.saveAndFlush(ControlOfPointOfSale.create(data));
    }
}
