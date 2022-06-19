package dev.facturador.account.application.query.get;

import dev.facturador.account.application.usecases.GetAccountUseCase;
import dev.facturador.account.domain.Account;
import dev.facturador.global.application.querys.QueryHandler;
import dev.facturador.global.domain.exception.ResourceNotFound;
import org.springframework.stereotype.Component;

/**Maneja la Query de {@link AccountGetQuery} retornado la entidad para ser mas generico*/
@Component
public class AccountGetQuetyHandler implements QueryHandler<Account, AccountGetQuery> {
    private final GetAccountUseCase useCase;

    public AccountGetQuetyHandler(GetAccountUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public Account handle(AccountGetQuery query) throws ResourceNotFound {
        //Recupera la cuenta
        var user = useCase.handleGetAccount(query.getUsername());
        //Como puede que esta cuenta no exista comprueba que no haya venido vacía
        if (user.isEmpty()) {
            throw new ResourceNotFound("No existe una cuenta con este username");
        }
        return user.get();
    }
}
