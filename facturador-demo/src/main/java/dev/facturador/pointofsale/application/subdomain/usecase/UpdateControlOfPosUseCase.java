package dev.facturador.pointofsale.application.subdomain.usecase;

import dev.facturador.pointofsale.domain.subdomain.PointsOfSaleControlRepository;
import dev.facturador.pointofsale.domain.subdomain.PointsOfSaleControl;
import dev.facturador.pointofsale.domain.subdomain.PosControlData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UpdateControlOfPosUseCase {
    @Autowired
    private PointsOfSaleControlRepository repository;

    public void handle(PosControlData data) throws Exception {
        log.info("Data is: {}", data);
        repository.saveAndFlush(PointsOfSaleControl.create(data));
    }
}
