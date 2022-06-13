package dev.facturador.pointofsale.application.usecase;

import dev.facturador.pointofsale.domain.PointOfSale;
import dev.facturador.pointofsale.domain.PointOfSaleCreate;
import dev.facturador.pointofsale.domain.PointOfSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreatePointOfSaleUseCase {

    @Autowired
    private PointOfSaleRepository repository;

    public void handlePointOfSaleCreation(PointOfSaleCreate values) {
        var pointofsale = PointOfSale.create(values);
        repository.save(pointofsale);
    }


}
