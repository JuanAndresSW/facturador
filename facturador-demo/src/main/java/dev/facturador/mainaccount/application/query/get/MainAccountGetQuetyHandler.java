package dev.facturador.mainaccount.application.query.get;

import dev.facturador.mainaccount.application.usecases.GetMainAccountUseCase;
import dev.facturador.mainaccount.domain.MainAccount;
import dev.facturador.mainaccount.domain.exception.MainAccountNotExists;
import dev.facturador.shared.application.querys.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class MainAccountGetQuetyHandler implements QueryHandler<MainAccount, MainAccountGetQuery> {
    private GetMainAccountUseCase useCase;

    public MainAccountGetQuetyHandler(GetMainAccountUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public MainAccount handle(MainAccountGetQuery query) throws MainAccountNotExists {
        var user = useCase.handle(query.getMainAccountIdUsername().getUsername());
        if (user == null) {
            throw new MainAccountNotExists("No existe una cuenta con este username");
        }
        return user;
    }
}
