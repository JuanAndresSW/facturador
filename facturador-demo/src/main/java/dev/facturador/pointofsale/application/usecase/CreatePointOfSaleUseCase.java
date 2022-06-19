package dev.facturador.pointofsale.application.usecase;

import dev.facturador.pointofsale.domain.PointOfSale;
import dev.facturador.pointofsale.domain.PointOfSaleCreate;
import dev.facturador.pointofsale.domain.PointOfSaleRepository;
import dev.facturador.pointofsale.domain.subdomain.PointsOfSaleControlRepository;
import dev.facturador.pointofsale.domain.subdomain.PointsOfSaleControl;
import dev.facturador.pointofsale.domain.subdomain.PosControlData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**Caso de uso para crear un punto de venta*/
@Service
@Transactional
public class CreatePointOfSaleUseCase {

    @Autowired
    private PointOfSaleRepository repository;
    @Autowired
    private PointsOfSaleControlRepository controlRepository;

    public void handlerPointOfSaleCreation(PointOfSaleCreate values) {
        var pointofsale = PointOfSale.create(values);
        //Se crea el punto de venta
        repository.save(pointofsale);
        //Se actualiza el PointOfSaleControl
        controlRepository.saveAndFlush(PointsOfSaleControl.create(PosControlData.starter(
                values.getPosControl().getPointsOfSaleControlId(),
                values.getPosControl().getCurrentCount(),
                values.getPosControl().getTotalCount(), true)));

    }


}
