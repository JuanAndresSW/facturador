package dev.facturador.pointofsale.application.subdomain.usecase;

import dev.facturador.pointofsale.domain.subdomain.ControlOfPointOfSale;
import dev.facturador.pointofsale.domain.subdomain.ControlOfPointOfSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetControlOfPosUseCase {

    @Autowired
    private ControlOfPointOfSaleRepository repository;

    public ControlOfPointOfSale handle(Long traderID) throws Exception {
        var control = repository.findByTraderIdTrader(traderID);
        if (control.isEmpty()) {
            throw new Exception("Comerciante no existe");
        }
        return control.get();
    }
}
