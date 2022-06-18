package dev.facturador.pointofsale.application.subdomain.query;


import dev.facturador.global.application.querys.QueryHandler;
import dev.facturador.pointofsale.application.subdomain.usecase.GetControlOfPosUseCase;
import dev.facturador.pointofsale.domain.subdomain.PointsOfSaleControl;
import org.springframework.stereotype.Component;

@Component
public class ControlOfPosGetQueryHandler
        implements QueryHandler<PointsOfSaleControl, ControlOfPosGetQuery> {

    private final GetControlOfPosUseCase useCase;

    public ControlOfPosGetQueryHandler(GetControlOfPosUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public PointsOfSaleControl handle(ControlOfPosGetQuery query)
            throws Exception {
        return this.useCase.handle(query.getTraderID());
    }
}
