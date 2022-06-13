package dev.facturador.pointofsale.application.usecase;

import dev.facturador.pointofsale.domain.PointOfSale;
import dev.facturador.pointofsale.domain.PointOfSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletePointOfSaleUseCase {
    @Autowired
    private PointOfSaleRepository repository;

    public void handlePointOfSaleDelete(Long pointOfSaleId) {
        var pointofsale = PointOfSale.create(pointOfSaleId);
        repository.delete(pointofsale);
    }
}
