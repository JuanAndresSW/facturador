package dev.facturador.pointofsale.application.subdomain.usecase;

import dev.facturador.pointofsale.domain.subdomain.PointsOfSaleControlRepository;
import dev.facturador.pointofsale.domain.subdomain.PointsOfSaleControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetControlOfPosUseCase {

    @Autowired
    private PointsOfSaleControlRepository repository;

    public PointsOfSaleControl handle(Long traderID) throws Exception {
        var control = repository.findByTraderTraderId(traderID);
        if (control.isEmpty()) {
            throw new Exception("Comerciante no existe");
        }
        return control.get();
    }
}
