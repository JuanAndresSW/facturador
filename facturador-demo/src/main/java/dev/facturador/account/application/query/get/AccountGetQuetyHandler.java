package dev.facturador.account.application.query.get;

import dev.facturador.account.application.usecases.GetAccountUseCase;
import dev.facturador.account.domain.Account;
import dev.facturador.global.application.querys.QueryHandler;
import dev.facturador.global.domain.exception.ResourceNotFound;
import org.springframework.stereotype.Component;

@Component
public class AccountGetQuetyHandler implements QueryHandler<Account, AccountGetQuery> {
    private final GetAccountUseCase useCase;

    public AccountGetQuetyHandler(GetAccountUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public Account handle(AccountGetQuery query) throws ResourceNotFound {
        var user = useCase.handleGetAccount(query.getMainAccountIdUsername());
        if (user.isEmpty()) {
            throw new ResourceNotFound("No existe una cuenta con este username");
        }
        return user.get();
    }
}
