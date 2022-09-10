package dev.facturador.pointofsale.application.subdomain.handlers;

import dev.facturador.global.domain.abstractcomponents.query.QueryHandler;
import dev.facturador.pointofsale.application.subdomain.PointsOfSaleControlRepository;
import dev.facturador.pointofsale.domain.subdomain.ControlOfPosGetQuery;
import dev.facturador.pointofsale.domain.subdomain.PointsOfSaleControl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ControlOfPosGetQueryHandler
        implements QueryHandler<PointsOfSaleControl, ControlOfPosGetQuery> {
    private final PointsOfSaleControlRepository repository;


    @Override
    public PointsOfSaleControl handle(ControlOfPosGetQuery query)
            throws Exception {
        var control = repository.findByTraderTraderId(query.getTraderID());

        if (control.isEmpty()) {
            throw new Exception("No se ha encontrado al comerciante");
        }
        return control.get();
    }
}
