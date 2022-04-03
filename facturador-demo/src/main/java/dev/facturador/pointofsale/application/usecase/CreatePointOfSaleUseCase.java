package dev.facturador.pointofsale.application.usecase;

import dev.facturador.pointofsale.domain.PointOfSale;
import dev.facturador.pointofsale.domain.PointOfSaleCreate;
import dev.facturador.pointofsale.domain.PointOfSaleRepository;
import dev.facturador.trader.domain.Trader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreatePointOfSaleUseCase {

    @Autowired
    private PointOfSaleRepository repository;

    public void handle(PointOfSaleCreate value, Trader trader){
        var pointOfSale = PointOfSale.create(value, trader);
        repository.save(pointOfSale);
    }

}
