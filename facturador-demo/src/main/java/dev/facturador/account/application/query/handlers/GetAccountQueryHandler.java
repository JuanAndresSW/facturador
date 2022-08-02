package dev.facturador.account.application.query.handlers;

import dev.facturador.account.application.query.GetAccountQuery;
import dev.facturador.account.application.usecases.GetAccountUseCase;
import dev.facturador.account.domain.Account;
import dev.facturador.global.application.querys.QueryHandler;
import dev.facturador.global.domain.exception.ResourceNotFound;
import org.springframework.stereotype.Component;

/**Maneja la Query de {@link GetAccountQuery} retornado la entidad para ser mas generico*/
@Component
public class GetAccountQueryHandler implements QueryHandler<Account, GetAccountQuery> {
    private final GetAccountUseCase useCase;

    public GetAccountQueryHandler(GetAccountUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public Account handleGetBranch(GetAccountQuery query) throws ResourceNotFound {
        //Recupera la cuenta
        var user = useCase.handleGetTraderSummary(query.getUsername());
        //Como puede que esta cuenta no exista comprueba que no haya venido vacía
        if (user.isEmpty()) {
            throw new ResourceNotFound("No existe una cuenta con este username");
        }
        return user.get();
    }
}
