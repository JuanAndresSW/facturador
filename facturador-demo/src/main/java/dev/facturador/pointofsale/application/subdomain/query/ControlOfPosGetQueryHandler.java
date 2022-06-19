package dev.facturador.pointofsale.application.subdomain.query;


import dev.facturador.pointofsale.application.subdomain.usecase.GetControlOfPosUseCase;
import dev.facturador.pointofsale.domain.subdomain.ControlOfPointOfSale;
import dev.facturador.shared.application.querys.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class ControlOfPosGetQueryHandler
        implements QueryHandler<ControlOfPointOfSale, ControlOfPosGetQuery> {

    private GetControlOfPosUseCase useCase;

    public ControlOfPosGetQueryHandler(GetControlOfPosUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public ControlOfPointOfSale handle(ControlOfPosGetQuery query)
            throws Exception {
        return this.useCase.handle(query.getTraderID());
    }
}
